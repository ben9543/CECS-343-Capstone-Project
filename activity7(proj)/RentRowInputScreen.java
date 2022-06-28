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
  
    private boolean isValidName(){
      for (Tenant t:tl.getTenants()){
        tenant.put(t.getName(),t.getAptNum());
      }
      if(tenant.containsKey(name)){ //check if name is in the tenantList
       return true;
      }
        return false;
    }
    private boolean isValidMonth(){
        return (month<=12 && month >0);
    }
  
    public void getInput(){
      Scanner in = new Scanner(System.in);
      System.out.println("Enter tenant's' name:");
      name = in.nextLine();
      if(isValidName()){
          System.out.println("Inserted invalid name.");
      } else {
          return;
      }
      System.out.println("Enter amount paid:");
      rent = in.nextDouble();
      System.out.println("Enter month rent is for(1-12):");
      month = in.nextInt();
      if (isValidMonth()){
          System.out.println("Inserted invalid month.");
      } else {
          return;
      }
      if(isValidName()){
            rr.insertRent(aptNum, month, rent);
      } 
      // in.close();
    }
}