class Tenant {
    private String name;
    private int aptNum; // three digits
    Tenant(){}
    Tenant(String name, int aptNum){
        this.name = name;
        this.aptNum = aptNum;
    }

    // Getters
    public String getName(){return this.name;}
    public int getAptNum(){return this.aptNum;}

    public String toString(){
        return String.format("%-15s%-15s", this.getName(), this.getAptNum());
    }
}