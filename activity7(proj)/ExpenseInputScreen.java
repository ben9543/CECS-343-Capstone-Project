import java.util.Scanner;

class ExpenseInputScreen {
  private int month, day;
  private String category, payee;
  private double amount;
  private ExpenseRecord exRc;
  private ExpenseRecord er;

  ExpenseInputScreen(ExpenseRecord er){
      this.er = er;
  }
    
  public void getInput(){
    Scanner in = new Scanner( System.in );
    System.out.println("Enter month (1-12):");
    month = in.nextInt();//check range?      
    System.out.println("Enter day (1-31):");
    day = in.nextInt();//check range?
    System.out.println("Enter expense category (Repairing, Utilities):");
    category = in.nextLine();
    System.out.println("Enter payee:");
    payee = in.nextLine();
    System.out.println("Enter amount:");
    amount = in.nextDouble();
    Expense e = new Expense(month,day, payee, amount, category);
    if(isValid()){
       exRc.insertExpense(e);
    }
    // in.close();
    }
    private boolean isValid(){
    //boolean valid = false;
    if(month<=12 && month >=1){
      if(day<=31 && day>=1)return true;
      else return false;
    } else {
        return false;
    }
  }
}