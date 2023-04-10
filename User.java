package src;

import javax.swing.*;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private Investment[] investments;
    private double annualSalary;
    private double buyingPrice;
    private double sellingPrice;
    private int years;
    private boolean resident;

    public User() {
    }

    public User(String name, double annualSalary, double buyingPrice, double sellingPrice, int years, boolean resident) {
        this.name = name;
        this.investments = null;
        this.annualSalary = annualSalary;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.years = years;
        this.resident = resident;
    }

    public User(String name, Investment[] investments, double annualSalary, double buyingPrice, double sellingPrice, int years, boolean resident) {
        this.name = name;
        this.investments = investments;
        this.annualSalary = annualSalary;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.years = years;
        this.resident = resident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Investment[] getInvestments() {
        return investments;
    }

    public void setInvestments(Investment[] investments) {
        this.investments = investments;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public double getProfit() {
        return (sellingPrice - buyingPrice);
    }

    public double getCgtProfit() {
        return (getProfit() / years);
    }

    public double getCgt() {
        double totalAnnualIncome = (annualSalary + getProfit());
        double taxRate = 0.0;
        if (this.isResident()) {
            if (totalAnnualIncome >= 0 && totalAnnualIncome < 18200) {
                taxRate = 0.0;
            } else if (totalAnnualIncome > 18200 && totalAnnualIncome < 45000) {
                taxRate = 19.0;
            } else if (totalAnnualIncome >= 45000 && totalAnnualIncome < 120000) {
                taxRate = 32.5;
            } else if (totalAnnualIncome >= 120000 && totalAnnualIncome < 180000) {
                taxRate = 37.0;
            } else if (totalAnnualIncome > 180000) {
                taxRate = 45.0;
            }
        } else {
            if (totalAnnualIncome >= 0 && totalAnnualIncome < 120000) {
                taxRate = 32.5;
            } else if (totalAnnualIncome > 120000 && totalAnnualIncome < 180000) {
                taxRate = 37.0;
            } else if (totalAnnualIncome > 180000) {
                taxRate = 45.0;
            }
        }
        return taxRate / 100 * getCgtProfit();
    }

    public double getActualProfit() {
        return getCgtProfit() - getCgt();
    }

    public void display() {


        //System.out.println("Name : " + name);
        //System.out.println("Residency status : " + resident);
        //System.out.println("Buying price : " + buyingPrice);
        //System.out.println("Selling price : " + sellingPrice);
        double cgt = getCgt();
        //System.out.println(" CGT : " + cgt);
        double actualProfit = getActualProfit();
        //System.out.println("Actual Profit : " + actualProfit);
        double remainingAmount = actualProfit;
        if (this.getInvestments() != null) {
            remainingAmount -= getInvestments()[0].getYear1Deposit();
            if (getInvestments()[1] != null) {
                remainingAmount -= getInvestments()[1].getYear1Deposit();
            }
        } else {
            JOptionPane.showMessageDialog(null,"No Investments!");
        }
        JOptionPane.showMessageDialog(null,"The remaining amount possible to be invested in the first year : " + remainingAmount);
        if (this.getInvestments() != null) {
            this.getInvestments()[0].display();
            if (this.getInvestments()[1] != null) {
                this.getInvestments()[1].display();
            }
        }

        String message = "<html><body>"
                         + "<h3>Name : </h3>" + name
                         + "<h3>Residency status : </h3>" + resident
                         + "<h3>Buying price : </h3>" + buyingPrice
                         + "<h3>Selling price : </h3>" + sellingPrice
                         + "<h3>CGT : </p>" + cgt
                         + "<h3>Actual Profit : </h3>" + actualProfit
                         + "<h3>The remaining amount possible to be invested in the first year : </h3>" + remainingAmount
                         + "</body></html>";
        JOptionPane.showMessageDialog(null,message);
    }
}
