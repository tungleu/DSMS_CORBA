package clients;

import DSMSApp.DSMS;
import DSMSApp.DSMSHelper;
import common.Province;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Customer {
    static DSMS dsms;
    private String customerID;
    private Province province;
    private Logger logger = null;

    public Customer(String customerID, Province province) {
        this.customerID = customerID;
        this.province = province;
        this.logger = startLogger();
        logger.info("Customer client is created with ID: " + this.customerID);
    }

    public Logger startLogger() {
        Logger logger = Logger.getLogger("CustomerLog");
        FileHandler fh;
        try {
            fh = new FileHandler("src/logs/ClientLogs/" + this.customerID + "_Client.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your province: QC, ON, BC");
        String input = scanner.next();
        Province province = null;
        switch (input) {
            case "ON":
                province = Province.ON;
                break;
            case "QC":
                province = Province.QC;
                break;
            case "BC":
                province = Province.BC;
                break;
        }
        System.out.println("Please enter your id");
        String IDNumber = scanner.next();
        String clientID = null;
        clientID = province.toString() + "U" + IDNumber;
        System.out.println("Your ID is :" + clientID);
        Customer customer = new Customer(clientID, province);
        try{
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            dsms = (DSMS) DSMSHelper.narrow(ncRef.resolve_str(customer.province.toString()));
            dsms.addCustomerClient(customer.customerID);
            int customerOption;
            String itemID;
            String inputDate;
            String itemName;
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
            Date date;
            while (true) {
                System.out.println("Please choose your action ");
                System.out.println("1.Purchase Item");
                System.out.println("2. Find Item ");
                System.out.println("3. Return Item ");
                customerOption = scanner.nextInt();
                switch(customerOption){
                    case 1:
                        System.out.println("PURCHASE SELECTED");
                        System.out.println("Enter item ID");
                        itemID = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the date of purchase in this form: MMMM dd, yyyy ");
                        inputDate = scanner.nextLine();
                        String result = dsms.purchaseItem(customer.customerID,itemID,inputDate);
                        System.out.println(result);
                        switch (result) {
                            case "SUCCESSFUL":
                                System.out.println("Item purchased successfully!");
                                break;
                            case "OUT OF BUDGET":
                                System.out.println("Insufficient budget to purchase item! ");
                                break;
                            case "OUT OF STOCK":
                                System.out.println("Item is out of stock, would you like to be in waitlist ?");
                                String option = scanner.next();
                                if (option.equals("yes")) {
                                    dsms.addWaitList(customer.customerID, itemID);
                                    System.out.println("Added to the waitlist");
                                }
                                break;
                            case "WRONG ID":
                                System.out.println("The given item id does not exist");

                                break;
                            default:
                                System.out.println(result);
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("FIND ITEM SELECTED");
                        System.out.println("Enter the name of item:");
                        itemName = scanner.next();
                        dsms.findItem(customer.customerID,itemName);
                        break;
                    case 3:
                        System.out.println("RETURN ITEM SELECTED");
                        System.out.println("Enter item ID");
                        itemID = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the date of return in this form: MMMM dd, yyyy ");
                        inputDate = scanner.nextLine();
                        boolean returnResult = dsms.returnItem(customer.customerID,itemID,inputDate);
                        if (returnResult){
                            System.out.println("Return successful");
                        }
                        else{
                            System.out.println("Return unsuccessful");
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR : " + e) ;
                e.printStackTrace(System.out);
            }
    }
}

