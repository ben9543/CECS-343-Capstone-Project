class AnnualReport {
  private RentRecord rentRecord;
  private ExpenseRecord expenseRecord;
  private double expenses = 0;
  private double rents = 0;

    AnnualReport(RentRecord rentRecord, ExpenseRecord expenseRecord){
        this.rentRecord = rentRecord;
        this.expenseRecord = expenseRecord;
    }

    public void display () {

        // Calculate the report
        getAllSum();
        getAmountExpense();

        // Start print
        System.out.println();
        System.out.println (String.format("Annual Summary"));
        System.out.println ("----------------------");
        System.out.println ("Income Rent: " + this.rents);
        System.out.println ("Income Expenses: " + this.expenses);
        
        /* This can be expense by catetories

        for(Expense e: expenseRecord.getExpenseRecords()){
            System.out.println ();
        }

        */
    }

    private void getAllSum(){
        for(RentRow r: rentRecord.getRentRecords()) {
            rents+=r.getSumOfRow();
        }
    }

    private void getAmountExpense() {
        for(Expense e: expenseRecord.getExpenseRecords()){
            expenses += e.getAmount();
        }
    }
}