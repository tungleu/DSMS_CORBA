package DSMS;

import DSMSApp.DSMSPOA;
import common.Province;
import org.omg.CORBA.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.NotBoundException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DSMS_Impl extends DSMSPOA {
    private ORB orb;
    private Map<String, String> store = new HashMap<String, String>();
    private Map<String, PriorityQueue<String>> waitlist = new HashMap<String, PriorityQueue<String>>();
    private Province province;
    private Logger logger = null;
    private HashMap<String, customerClient> customerClients = new HashMap<String, customerClient>();
    private HashMap<String, managerClient> managerClients = new HashMap<String, managerClient>();
    private HashMap<String, Integer> portMap = new HashMap<String, Integer>();
    private ArrayList<String> purchaseLog = new ArrayList<String>();
    public DSMS_Impl(Province province, ORB orb) throws IOException {
        super();
        this.orb = orb;
        this.province = province;
        this.portMap.put("QC", 1111);
        this.portMap.put("ON", 2222);
        this.portMap.put("BC", 3333);
        this.logger = this.startLogger();
        logger.info("Server " + this.province.toString()+ " has started");
    }

    public Logger startLogger() {
        Logger logger = Logger.getLogger("ServerLog");
        FileHandler fh;
        try {
            fh = new FileHandler("src/logs/ServerLogs/"+this.province.toString()+"_Server.log");
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

    @Override
    public boolean addItem(String managerID, String itemID, String itemName, int quantity, int price) {
        return false;
    }

    @Override
    public boolean removeItem(String managerID, String itemID, int quantity) {
        return false;
    }

    @Override
    public String listItemAvailability(String managerID) {
        return null;
    }

    @Override
    public String purchaseItem(String customerID, String itemID, String dateOfPurchase) {
        return null;
    }

    @Override
    public String findItem(String customerID, String itemName) {
        return null;
    }

    @Override
    public String returnItem(String customerID, String itemID, String dateOfReturn) {
        return null;
    }

    @Override
    public String exchangeItem(String customerID, String newitemID, String oldItemID) {
        return null;
    }

    @Override
    public void addWaitList(String customerID, String itemID) {

    }

    @Override
    public void addCustomerClient(String customerID) {

    }

    @Override
    public void addManagerClient(String managerID) {

    }

    @Override
    public void shutdown() {

    }
    public void receive(){
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(this.portMap.get(this.province.toString()));
            byte[] buffer = new byte[1000];
            System.out.println("UDP Server for "+this.province.toString() + " has started listening............");
            String replyMessage = null;
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String[] requestParam = new String(request.getData()).split(",");
                if(requestParam[0].equals("PURCHASE")){
                    String customerID = requestParam[1];
                    int budget = Integer.parseInt(requestParam[2]);
                    String itemID = requestParam[3];
                    Date date = new Date(requestParam[4]);
//                    replyMessage = this.purchaseFromOutside(customerID,budget,itemID, date);
                }else if(requestParam[0].equals("WAITLIST")){
                    //ADD WAITLIST TO OTHER SERVER
                    String customerID = requestParam[1];
                    String itemID = requestParam[2];
//                    this.addLocalWaitList(customerID,itemID);
                }else if(requestParam[0].equals("ITEM_INFO")){
                    String itemName = requestParam[1];
//                    replyMessage = this.findLocalItem(itemName);
                }else if(requestParam[0].equals("RETURN")){
                    String customerID = requestParam[1];
                    String itemID = requestParam[2];
                    Date dateOfReturn = new Date(requestParam[3]);
//                    replyMessage = this.returnItemfromOutside(customerID,itemID,dateOfReturn);
                }
                else if(requestParam[0].equals("PURCHASE_2")){
                    String customerID = requestParam[1];
                    String itemID = requestParam[2];
                    Date date = new Date(requestParam[3]);
//                    replyMessage = this.purchaseItem(customerID,itemID, date);
                }
                DatagramPacket reply = new DatagramPacket(replyMessage.getBytes(), replyMessage.length(), request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}
