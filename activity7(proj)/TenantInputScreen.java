import java.util.Scanner;
import java.util.HashMap;

class TenantInputScreen {
    
    private TenantList tl;
    private String name;
    private int aptNum;

    HashMap<String,Integer> tenant = new HashMap<String,Integer>();

    TenantInputScreen(TenantList tl){
        this.tl = tl;
    }
    
    public void getInput(){
      System.out.println("Enter tenant's name:");//Ask for name
      Scanner in = new Scanner( System.in );
      name = in.nextLine();
      if(!isValidName()){
          System.out.println("The name is already in the tenant list.");
          return;
      }
      System.out.println("Enter the apartment number:");//Ask for apt#
      aptNum = in.nextInt();
      if(!isValidAptNum()){
          System.out.println("The apartment is already occupied.");
          return;
      }
      Tenant t = new Tenant(name,aptNum);
      tl.insertTenant(t);
    
      // in.close();//close scanner
    }
  
    private boolean isValidName(){
        for (Tenant t:tl.getTenants()){
            if(t.getName().equals(this.name)){
                return false;
            }
        }//end isValid
        return true;
    }

    private boolean isValidAptNum(){
        for (Tenant t:tl.getTenants()){
            if(t.getAptNum() == this.aptNum){
                return false;
            }
        }//end isValid
        return true;
    }
}//end class
