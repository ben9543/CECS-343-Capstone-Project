import java.util.Scanner;
import java.io.IOException;
class UserInterface {

    /* Data models */
    private TenantList tl;
    private RentRecord rr;
    private ExpenseRecord er;
    private AnnualReport ar;
    private DBconnect db;

    /* Input screens */
    private TenantInputScreen ti;
    private RentRowInputScreen ri;
    private ExpenseInputScreen ei;

    /* Member variables */
    private OutputScreen output;
    private char ch = ' ';
    private Scanner sc = new Scanner(System.in);

    UserInterface(){
        this.tl = new TenantList();
        this.rr = new RentRecord();
        this.er = new ExpenseRecord();
        this.ti = new TenantInputScreen(this.tl);
        this.ri = new RentRowInputScreen(this.tl, this.rr);
        this.ei = new ExpenseInputScreen(this.er);
        this.ar = new AnnualReport(rr, er);
        this.output = new OutputScreen(tl, rr, er, ar);
        this.db = new DBconnect(tl, rr, er);
    }

    /* Public */
    public void interact() throws IOException {
        getUserInputForMenu();
        if (ch == 'd'){

            // Get user input
            getUserInputForOutput();

            // Using OutputScreen to display
            output.display(this.ch);
            
        } else if (ch=='i'){
            
            // Get user input
            getUserInputForInsert();

            // Insert data 
            insertData();
            
        } else {
            System.out.println("\nBye!\n");
            saveAlltoDB();
            sc.close();
            System.exit(0);
        }
    }

    /* Private */
    private void saveAlltoDB() throws IOException {
        this.db.saveAll();
    }

    private void insertData(){
        if (this.ch == 't'){
            this.ti.getInput();
        } else if(this.ch=='r'){
            this.ri.getInput();
        } else if(this.ch=='e'){
            // this.ei.getInput();
            System.out.println("In progress");
        }
    }
    
    private void getUserInputForMenu(){
        char temp = ' ';
        while (!isValidMenuInput(temp)){
            printStartMenu();
            temp = sc.next().charAt(0);
        }
        this.ch = temp;
    }
    private void getUserInputForOutput(){
        char temp = ' ';
        while (!isValidOutputInput(temp)){
            printOutputMenu();
            temp = sc.next().charAt(0);
        }
        this.ch = temp;
    }
    private void getUserInputForInsert(){
        char temp = ' ';
        while (!isValidInsertInput(temp)){
            printInsertMenu();
            temp = sc.next().charAt(0);
        }
        this.ch = temp;
    }
    
    private boolean isValidMenuInput(char ch){
        return (ch=='d' || ch=='i' || ch=='q');
    }
    private boolean isValidOutputInput(char ch){
        return (ch=='t' || ch=='r' || ch=='e' || ch=='a');
    }
    private boolean isValidInsertInput(char ch){
        return (ch=='t' || ch=='r' || ch=='e');
    }

    // Printing
    private void printStartMenu(){
        System.out.println("\n==========================");
        System.out.println("Enter\n'i' to input data,\n'd' to display a report,\n'q' to quit program:");
        System.out.println("==========================\n");
    }

    private void printOutputMenu(){
        System.out.println("\n==========================");
        System.out.println("Enter\n't' to display tenants,\n'r' to display rents,\n'e' to display expenses,\n'a' to display annual report:");

        System.out.println("==========================\n");
    }

    private void printInsertMenu(){
        System.out.println("\n==========================");
        System.out.println("Enter\n't' to add tenant,\n'r' to record rent payment,\n'e' to record expense:");
        System.out.println("==========================\n");
    }
}