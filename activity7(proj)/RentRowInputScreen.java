import java.util.Scanner;
import java.util.HashMap;

class RentRowInputScreen {
    private int aptNum;
    private double rent;
    private String name;
    private int month;
    private TenantList tl;
    private RentRecord rr;
    HashMap<String,Integer> tenant;
    
    RentRowInputScreen(TenantList tl, RentRecord rr){
        this.tl = tl;
        this.rr = rr;
        tenant = new HashMap<String,Integer>();
    }
  
    private boolean isValid(){
      for (Tenant t:tl.getTenants()){
        tenant.put(t.getName(),t.getAptNum());
      }
      if(tenant.containsKey(name)==true){ //check if name is in the tenantList
        if(month<=12 && month>=1){ //month is 1-12
          aptNum = tenant.get(name); //set aptNum
          return true;
        }
      }
        return false;
    }
  
    public void getInput(){
      Scanner in = new Scanner(System.in);
      System.out.println("Enter tenant's' name:");
      name = in.nextLine();
      System.out.println("Enter amount paid:");
      rent = in.nextDouble();
      System.out.println("Enter month rent is for(1-12):");
      month = in.nextInt();
      if(isValid()){
            rr.insertRent(aptNum, month, rent);
        }
      // in.close();
    }
}