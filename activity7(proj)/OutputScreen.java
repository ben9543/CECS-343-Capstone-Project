class OutputScreen {
    private TenantList tl;
    private RentRecord rr;
    private ExpenseRecord er;
    private AnnualReport ar;
    
    OutputScreen(TenantList tl, RentRecord rr, ExpenseRecord er, AnnualReport ar){
        this.tl = tl;
        this.rr = rr;
        this.er = er;
        this.ar = ar;
    }

    void display(char ch){
        if (ch == 't') tl.display();
        else if(ch == 'r') rr.display();
        else if(ch == 'e') er.display();
        else if(ch == 'a') ar.display();
        else {// Error needs to be handled before char input  
            System.out.println("ERROR: Invalid input");
        }
    }
}