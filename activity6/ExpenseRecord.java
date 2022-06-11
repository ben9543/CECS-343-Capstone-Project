import java.util.ArrayList;
class ExpenseRecord {
    private ArrayList<Expense>expenseRecords = new ArrayList<Expense>();
    public ArrayList<Expense> getExpenseRecords(){return expenseRecords;}
    ExpenseRecord(){}

    // month, day, amount validation in inputScreen
    public void insertExpense(Expense e){
        expenseRecords.add(e);
    }
    public void display(){
        String r = "";
        String l [] = {"Date", "Payee", "Amount", "Category"};
        for (String s : l){
            r+=String.format("%-15s", s);
        }
        System.out.println();
        System.out.println(r);
        System.out.println("-----------------------------------------------------------");
        for(Expense e: expenseRecords){
            System.out.println(e);
        }
        System.out.println();
    }
}