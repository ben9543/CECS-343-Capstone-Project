import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        
        // Initializing DB
        TenantList tl = new TenantList();
        RentRecord rr = new RentRecord();
        ExpenseRecord er = new ExpenseRecord();
        DBconnect db = new DBconnect(tl, rr, er);

        /* DB Testing code
        tl.insertTenant(new Tenant("Ben", 101));
        tl.insertTenant(new Tenant("Sam", 102));
        tl.insertTenant(new Tenant("Jasmine", 103));
        tl.insertTenant(new Tenant("Hina", 104));

        rr.insertRent(101, 2, 600);
        rr.insertRent(101, 3, 700);
        rr.insertRent(101, 7, 600);
        rr.insertRent(103, 5, 600);

        er.insertExpense(new Expense(12, 21, "Marbrisa Apt", 1140, "Utitlity Fees"));

        */
        
        tl.display();
        er.display();
        rr.display();
        
        db.saveAll();
    }
}
