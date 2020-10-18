package clients;

import common.Province;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Manager {
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
}
