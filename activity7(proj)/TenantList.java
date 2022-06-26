import java.util.ArrayList;

class TenantList {
    private ArrayList<Tenant>tenants = new ArrayList<Tenant>(20);
    public ArrayList<Tenant> getTenants() {return tenants;}
    
    // Return aptNum by name
    public int getAptNum(String name){
        for (Tenant x : tenants){
           if (x.getName().equals(name)){
               return x.getAptNum();
           }
        }
        return 0;
    }
    public void insertTenant(Tenant t){
        boolean isValid = true;
        for (Tenant x : tenants){
           if (!inputValidation(x, t)){
               isValid = false;
               break;
           }
        }
        if (isValid) {
            tenants.add(t);
        };
    }
    // TenantInputScreen will do more validation
    private boolean inputValidation(Tenant t1, Tenant t2){
        if (t1.getName().equals(t2.getName())){
            System.out.println("The tenant's name has to be unique.");
            return false;
        } else if(t1.getAptNum() == t2.getAptNum()) {
            System.out.println("The unit is already occupied.");
            return false;
        } else {
            return true;
        }
    }
    public void display(){
        System.out.println();
        System.out.println(String.format("%-15s%-15s", "Name", "APT"));
        System.out.println("----------------------");
        for (Tenant x : tenants)System.out.println(x);
        System.out.println();
    }
}