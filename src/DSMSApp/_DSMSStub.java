package DSMSApp;


/**
* DSMSApp/_DSMSStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dsms.idl
* Sunday, October 18, 2020 7:34:02 o'clock AM EDT
*/

public class _DSMSStub extends org.omg.CORBA.portable.ObjectImpl implements DSMSApp.DSMS
{

  public boolean addItem (String managerID, String itemID, String itemName, int quantity, int price)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addItem", true);
                $out.write_string (managerID);
                $out.write_string (itemID);
                $out.write_string (itemName);
                $out.write_long (quantity);
                $out.write_long (price);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return addItem (managerID, itemID, itemName, quantity, price        );
            } finally {
                _releaseReply ($in);
            }
  } // addItem

  public boolean removeItem (String managerID, String itemID, int quantity)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("removeItem", true);
                $out.write_string (managerID);
                $out.write_string (itemID);
                $out.write_long (quantity);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return removeItem (managerID, itemID, quantity        );
            } finally {
                _releaseReply ($in);
            }
  } // removeItem

  public String listItemAvailability (String managerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listItemAvailability", true);
                $out.write_string (managerID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listItemAvailability (managerID        );
            } finally {
                _releaseReply ($in);
            }
  } // listItemAvailability

  public String purchaseItem (String customerID, String itemID, String dateOfPurchase)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("purchaseItem", true);
                $out.write_string (customerID);
                $out.write_string (itemID);
                $out.write_string (dateOfPurchase);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return purchaseItem (customerID, itemID, dateOfPurchase        );
            } finally {
                _releaseReply ($in);
            }
  } // purchaseItem

  public String findItem (String customerID, String itemName)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("findItem", true);
                $out.write_string (customerID);
                $out.write_string (itemName);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return findItem (customerID, itemName        );
            } finally {
                _releaseReply ($in);
            }
  } // findItem

  public boolean returnItem (String customerID, String itemID, String dateOfReturn)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("returnItem", true);
                $out.write_string (customerID);
                $out.write_string (itemID);
                $out.write_string (dateOfReturn);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return returnItem (customerID, itemID, dateOfReturn        );
            } finally {
                _releaseReply ($in);
            }
  } // returnItem

  public boolean exchangeItem (String customerID, String newitemID, String oldItemID, String dateOfExchange)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("exchangeItem", true);
                $out.write_string (customerID);
                $out.write_string (newitemID);
                $out.write_string (oldItemID);
                $out.write_string (dateOfExchange);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return exchangeItem (customerID, newitemID, oldItemID, dateOfExchange        );
            } finally {
                _releaseReply ($in);
            }
  } // exchangeItem

  public void addWaitList (String customerID, String itemID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addWaitList", true);
                $out.write_string (customerID);
                $out.write_string (itemID);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                addWaitList (customerID, itemID        );
            } finally {
                _releaseReply ($in);
            }
  } // addWaitList

  public void addCustomerClient (String customerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addCustomerClient", true);
                $out.write_string (customerID);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                addCustomerClient (customerID        );
            } finally {
                _releaseReply ($in);
            }
  } // addCustomerClient

  public void addManagerClient (String managerID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addManagerClient", true);
                $out.write_string (managerID);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                addManagerClient (managerID        );
            } finally {
                _releaseReply ($in);
            }
  } // addManagerClient

  public void shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", false);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:DSMSApp/DSMS:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _DSMSStub
