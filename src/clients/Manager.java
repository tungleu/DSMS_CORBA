package clients;

import DSMSApp.*;
import common.Province;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Manager {
    static DSMS dsms;
    private String managerID;
    private Province province;
    private Registry registry = null;
    private Logger logger = null;
    public Manager(String managerID, Province province) throws Exception {
        this.managerID = managerID;
        this.province = province;
        this.registry = LocateRegistry.getRegistry(1111);
        this.logger = startLogger();
    }
    public Logger startLogger() {
        Logger logger = Logger.getLogger("ManagerLog");
        FileHandler fh;
        try {
            fh = new FileHandler("src/logs/ClientLogs/"+this.managerID+"_Client.log");
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

    public static void main(String[] args) throws Exception {
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
        String clientID = province.toString() + "M" + IDNumber;
        System.out.println("Your ID is :" + clientID);
        Manager manager = new Manager(clientID,province);
        try{
            String[] arguments = new String[] {"-ORBInitialPort","1234","-ORBInitialHost","localhost"};
            ORB orb = ORB.init(arguments, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            dsms = (DSMS) DSMSHelper.narrow(ncRef.resolve_str(manager.province.toString()));
            dsms.addCustomerClient(manager.managerID);
            int customerOption;
            String itemID;
            String itemName;
            int price;
            int quantity;
            while (true) {
                System.out.println("Please choose your action ");
                System.out.println("1. Add Item");
                System.out.println("2. Remove Item ");
                System.out.println("3. List Item ");
                customerOption = scanner.nextInt();
                switch(customerOption){
                    case 1:
                        System.out.println("ADD ITEM SELECTED");
                        System.out.println("Enter item ID");
                        itemID = scanner.next();
                        System.out.println("Enter item name");
                        itemName = scanner.next();
                        System.out.println("Enter price");
                        price = scanner.nextInt();
                        System.out.println("Enter quantity");
                        quantity = scanner.nextInt();
                        manager.logger.info("Manager client with ID: "+ manager.managerID +" " +
                                "sent a request to add an item with ID: "+itemID);
                        dsms.addItem(manager.managerID, itemID, itemName, quantity, price);
                        System.out.println("Added item successfully");
                        break;
                    case 2:
                        System.out.println("Remove ITEM SELECTED");
                        System.out.println("Enter item ID");
                        itemID = scanner.next();
                        System.out.println("Enter quantity");
                        quantity = scanner.nextInt();
                        manager.logger.info("Manager client with ID: "+ manager.managerID +" " +
                                "sent a request to add an item with ID: "+itemID);
                        dsms.removeItem(manager.managerID, itemID, quantity);
                        System.out.println("Removed item successfully");
                        break;
                    case 3:
                        System.out.println("LIST ITEM AVAILABILITY SELECTED");
                        manager.logger.info("Manager client with ID: "+ manager.managerID +" " +
                                "sent a request to listItemAvailability");
                        System.out.println(dsms.listItemAvailability(manager.managerID));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }
}
