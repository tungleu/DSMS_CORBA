package DSMS;

import common.Province;

public class managerClient {
    private String managerID;
    private Province province;
    public managerClient(Province province, String managerID){
        this.province = province;
        this.managerID = managerID;
    }
    public String getID(){
        return this.managerID;
    }
}
