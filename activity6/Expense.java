class Expense {
    private int month;
    private int day;
    private String category;
    private String payee;
    private double amount;
    Expense(){}

    // More validation with month&day in ExpenseInputScreen
    Expense(int month, int day, String payee, double amount, String category){
        this.month = month;
        this.day = day;
        this.payee = payee;
        this.amount = amount;
        this.category = category;
    }

    public String toString(){

        // 1/2 City Water 978 Utilities
        String date = this.month + "/" + this.day;
        String s[] = {date, payee, String.valueOf(amount), category};
        String r = "";
        for (String ss: s)
            r += String.format("%-15s", ss);
        return r;
    }

    // Getters
    public int getMonth(){return month;}
    public int getDay(){return day;}
    public String getCategory(){return category;}
    public String getPayee(){return payee;}
    public double getAmount(){return amount;}
}