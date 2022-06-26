import java.util.*;
import java.io.*;

class DBconnect {

    // Member variables
    private TenantList tl;
    private RentRecord rr;
    private ExpenseRecord er;

    // File names
    static final String tenantFile = "tenantList.txt";
    static final String rentRecordFile = "rentRecord.txt";
    static final String expenseRecordFile = "expenseRecord.txt";

    // Constructors
    DBconnect(){}
    DBconnect(TenantList tl, RentRecord rr, ExpenseRecord er){
        this.tl = tl;
        this.rr = rr;
        this.er = er;
        System.out.println("File creating ...");
        try {
            File tenant = new File(tenantFile);
            File rentRecord = new File(rentRecordFile);
            File expenseRecord = new File(expenseRecordFile);
            createNewFile(tenant);
            createNewFile(rentRecord);
            createNewFile(expenseRecord);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        loadTenantList();
        loadRentRecord();
        loadExpenseRecord();
    };

    private void createNewFile(File f)throws IOException{
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        } else {
            System.out.println("File " + f.getName() + " already exists.");
        }
    }

    private void writeToFile(String fileName, String row){
        try {
            FileWriter myWriter = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(myWriter);
            out.write(row);
            out.newLine();
            out.close();
            // System.out.println(fileName + ": " + "Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Save
    public void saveAll() throws IOException {
        try{
            File tenant = new File(tenantFile);
            File rentRecord = new File(rentRecordFile);
            File expenseRecord = new File(expenseRecordFile);
            PrintWriter writer;
                
            // 1. Cleaning up textfiles
            writer = new PrintWriter(tenant);
            writer.print("");
            writer.close();
            writer = new PrintWriter(rentRecord);
            writer.print("");
            writer.close();
            writer = new PrintWriter(expenseRecord);
            writer.print("");
            writer.close();
    
            // 2. Saving data
            saveTenantList();
            saveRentRecords();
            saveExpenseRecords();
            
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void saveTenantList(){
        for (Tenant x : tl.getTenants()){
            saveTenant(x);
        }
    }
    private void saveRentRecords(){
        for (RentRow x : rr.getRentRecords()){
            saveRentRow(x);
        }
    }
    private void saveExpenseRecords(){
        for (Expense x : er.getExpenseRecords()){
            saveExpense(x);
        }
    }
    private void saveTenant(Tenant t){
        String row = t.getName() + "," + t.getAptNum();
        writeToFile(tenantFile, row);
    }
    private void saveRentRow(RentRow r){
        int aptNum = r.getAptNum();
        double rents[] = r.getRents();
        String row = String.valueOf(aptNum)+",";
        for (int i = 0; i < 12; i++){
            if (i == 11) 
                row += rents[i];
            else 
                row += (rents[i] + ",");
        }
        writeToFile(rentRecordFile, row);
    }
    private void saveExpense(Expense e){
        // Date Payee Amount Category
        String s[] = {String.valueOf(e.getMonth()), String.valueOf(e.getDay()), e.getPayee(), String.valueOf(e.getAmount()), e.getCategory()};
        String row = String.join(",", s);
        writeToFile(expenseRecordFile, row);
    }

    // Read (in progress)
    private Tenant readTenant(String row){
        String[] l = row.split(",", 3);
        String name = l[0];
        int aptNum = Integer.parseInt(l[1]);
        return new Tenant(name, aptNum);
    }
    private RentRow readRentRow(String row){
        String[] l = row.split(",", 13);
        double rent[] = new double[12];
        int aptNum = Integer.parseInt(l[0]);
        for (int i = 1; i < l.length; i++){
            rent[i-1] = Double.parseDouble(l[i]);
        }
        return new RentRow(aptNum, rent);
    }
    private Expense readExpense(String row){
        // Month Day Payee Amount Category
        String[] l = row.split(",", 5);
        int month = Integer.parseInt(l[0]);
        int day = Integer.parseInt(l[1]);
        String payee = l[2];
        double amount = Double.parseDouble(l[3]);
        String category = l[4];
        return new Expense(month, day, payee, amount, category);
    }

    // Load (in progress)
    private void loadTenantList(){
        try {
            File myObj = new File(tenantFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Tenant t = readTenant(data);
                tl.insertTenant(t);
                // System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void loadRentRecord(){
        try {
            File myObj = new File(rentRecordFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                RentRow r = readRentRow(data);
                rr.insertRentRow(r);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void loadExpenseRecord(){
        try {
            File myObj = new File(expenseRecordFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Expense e = readExpense(data);
                er.insertExpense(e);
                // System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}