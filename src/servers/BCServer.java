package servers;

import DSMS.DSMS_Impl;
import DSMSApp.DSMS;
import DSMSApp.DSMSHelper;
import common.Province;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class BCServer {
    public static void main(String[] args) {
        try{

            String[] arguments = new String[] {"-ORBInitialPort","1234","-ORBInitialHost","localhost"};
            ORB orb = ORB.init(arguments, null);
// get reference to rootpoa& activate the POAManager
            POA rootpoa= POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
// create servant and register it with the ORB
            DSMS_Impl server= new DSMS_Impl(Province.BC,orb);

//get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(server);
            DSMS href = (DSMS) DSMSHelper.narrow(ref);
//get the root naming context
//NameServiceinvokes the name service
            org.omg.CORBA.Object objRef=
                    orb.resolve_initial_references("NameService");
//Use NamingContextExtwhich is part of the Interoperable Naming Service (INS) specification.
            NamingContextExt ncRef= NamingContextExtHelper.narrow(objRef);
//bind the Object Reference in Naming
            String name = "BC";
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, href);
            System.out.println("BC Server ready and waiting ...");
            server.receive();
//wait for invocations from clients
            orb.run();
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("BC ServerExiting ...");
    }
}

