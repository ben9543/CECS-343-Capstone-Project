import java.util.Scanner;
import java.util.HashMap;

class TenantInputScreen {
    
    private TenantList tl;
    private String name;
    private int aptNum;

    TenantInputScreen(TenantList tl){
        this.tl = tl;
    }
    
    public void getInput(){
      System.out.println("Enter tenant's name:");//Ask for name
      Scanner in = new Scanner( System.in );
      name = in.nextLine();
      System.out.println("Enter the apartment number:");//Ask for apt#
      aptNum = in.nextInt();        
      Tenant t = new Tenant(name,aptNum);
      if(isValid()){
        tl.insertTenant(t);
        tl.display();
      }
      // in.close();//close scanner
    }
  
    private boolean isValid(){
      boolean valid = false;
      HashMap<String,Integer> tenant = new HashMap<String,Integer>();
      for (Tenant t:tl.getTenants()){
        tenant.put(t.getName(),t.getAptNum());
        while(!valid){
          if(tenant.containsKey(name) == true || tenant.containsValue(aptNum) == true){ 
            //name or aptNum is exist in record ==> not a valid tenant
            return false;
        }else
            return true;
        }
      }
      return false;
    }//end isValid
  
}//end class
