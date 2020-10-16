package DSMSApp;


/**
* DSMSApp/DSMSOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dsms.idl
* Thursday, October 15, 2020 8:18:26 o'clock PM EDT
*/

public interface DSMSOperations 
{
  boolean addItem (String managerID, String itemID, String itemName, int quantity, int price);
  boolean removeItem (String managerID, String itemID, int quantity);
  String listItemAvailability (String managerID);
  String purchaseItem (String customerID, String itemID, String dateOfPurchase);
  String findItem (String customerID, String itemName);
  String returnItem (String customerID, String itemID, String dateOfReturn);
  String exchangeItem (String customerID, String newitemID, String oldItemID);
  void addWaitList (String customerID, String itemID);
  void addCustomerClient (String customerID);
  void addManagerClient (String managerID);
  void shutdown ();
} // interface DSMSOperations