import java.util.*;

class RentRow {
    private int aptNum;
    private double rents[] = new double[12];
    RentRow(int aptNum){
        this.aptNum = aptNum;
    }
    RentRow(int aptNum, double rents[]){
        this.aptNum = aptNum;
        this.rents = rents;
    }
    public void setRent(int month, double rent){
        this.rents[month-1] = rent;
    }

    public double getSumOfRow(){
        double sum = 0;
        for (double d : rents){
            sum+=d;
        }
        return sum;
    }

    // Getters
    public int getAptNum(){return this.aptNum;}
    public double[] getRents(){return this.rents;}
    public String toString(){
        String r = String.format("%-8s", String.valueOf(aptNum));
        for (double d: rents){
            r += String.format("%-8s", d);
        }
        return r;
    }
}