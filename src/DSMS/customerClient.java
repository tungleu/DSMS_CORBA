package DSMS;

import common.Province;

import java.io.PrintWriter;
import java.util.HashMap;

public class customerClient{
    private String customerID;
    private int budget = 1000;
    private Province province;
    private PrintWriter pw ;
    private HashMap<String, Boolean> elgibility = new HashMap<String, Boolean>();
    public customerClient(Province province, String customerID){
        this.province = province;
        this.customerID = customerID;
        this.elgibility.put("QC",true);
        this.elgibility.put("ON",true);
        this.elgibility.put("BC",true);
    }
    public String getID(){
        return this.customerID;
    }
    public int getBudget(){
        return this.budget;
    }
    public void setBudget(int budget){
        this.budget = budget;
    }
    public void setElgibility(String province,boolean eligibility){
        this.elgibility.replace(province,eligibility);
    }
    public boolean checkEligible(String province){
        return this.elgibility.get(province);
    }


}