import java.util.ArrayList;
class RentRecord {
    private ArrayList<RentRow>rentRecords = new ArrayList<RentRow>(20);
    public ArrayList<RentRow> getRentRecords(){return rentRecords;}
    public void insertRentRow(RentRow r){
        rentRecords.add(r);
    }

    // More validation in RentInputScreen
    public void insertRent(int aptNum, int month, double rent){
        boolean doesExist = false;
        for (RentRow x : rentRecords){
            if(x.getAptNum() == aptNum){
                doesExist = true;
                x.setRent(month, rent);
                break;
            }
        }
        if (!doesExist){
            RentRow r = new RentRow(aptNum);
            r.setRent(month, rent);
            rentRecords.add(r);
        }
    }
    public void display(){
        String l[] = {"APT", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        System.out.println();
        for (String s: l)System.out.format("%-8s", s);
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for (RentRow x : rentRecords)System.out.printf("%s\n", x);
        System.out.println();
    }
}