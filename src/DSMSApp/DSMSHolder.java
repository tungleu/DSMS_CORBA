package DSMSApp;

/**
* DSMSApp/DSMSHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from dsms.idl
* Thursday, October 15, 2020 8:18:26 o'clock PM EDT
*/

public final class DSMSHolder implements org.omg.CORBA.portable.Streamable
{
  public DSMSApp.DSMS value = null;

  public DSMSHolder ()
  {
  }

  public DSMSHolder (DSMSApp.DSMS initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DSMSApp.DSMSHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DSMSApp.DSMSHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DSMSApp.DSMSHelper.type ();
  }

}
