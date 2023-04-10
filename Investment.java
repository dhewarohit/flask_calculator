package src;

import javax.swing.*;
import java.io.Serializable;

public class Investment implements Serializable {
    private double year1Deposit;
    private double year2Deposit;
    private double year3Deposit;

    private double year1Profit;
    private double year2Profit;
    private double year3Profit;

    private double year1TotalProfit;
    private double year2TotalProfit;
    private double year3TotalProfit;
    private int coinSelection;

    Investment(double year1Dep, double year2Dep, double year3Dep) {
        this.setYear1Deposit(year1Dep);
        this.setYear2Deposit(year2Dep);
        this.setYear3Deposit(year3Dep);
    }

    public double getYear1Deposit() {
        return year1Deposit;
    }

    public void setYear1Deposit(double year1Deposit) {
        this.year1Deposit = year1Deposit;
    }

    public double getYear2Deposit() {
        return year2Deposit;
    }

    public void setYear2Deposit(double year2Deposit) {
        this.year2Deposit = year2Deposit;
    }

    public double getYear3Deposit() {
        return year3Deposit;
    }

    public void setYear3Deposit(double year3Deposit) {
        this.year3Deposit = year3Deposit;
    }

    // constructor
    public Investment() {
    }

    public void setCoinSelection(int inputSelection) {
        coinSelection = inputSelection;
    }

    public int getCoinSelection() {
        return (coinSelection);
    }

    public Investment calcInvestment(int coin, double year1Deposit, double year2Deposit, double year3Deposit) {
        if (coin == 1) {
            this.year1Profit = this.year1TotalProfit = 0.18 * year1Deposit;
            this.year2Profit = 0.18 * (year1Deposit + year2Deposit);
            this.year2TotalProfit = this.year1Profit + this.year2Profit;
            this.year3Profit = 0.18 * (year1Deposit + year2Deposit + year3Deposit);
            this.year3TotalProfit = this.year1Profit + this.year2Profit + this.year3Profit;
        } else if (coin == 2) {
            this.year1Profit = this.year1TotalProfit = 0.12 * year1Deposit;
            this.year2Profit = 0.12 * (year1Deposit + year2Deposit);
            this.year2TotalProfit = this.year1Profit + this.year2Profit;
            this.year3Profit = 0.12 * (year1Deposit + year2Deposit + year3Deposit);
            this.year3TotalProfit = this.year1Profit + this.year2Profit + this.year3Profit;
        } else if (coin == 3) {
            this.year1Profit = this.year1TotalProfit = 0.15 * year1Deposit;
            this.year2Profit = 0.15 * (year1Deposit + year2Deposit);
            this.year2TotalProfit = this.year1Profit + this.year2Profit;
            this.year3Profit = 0.15 * (year1Deposit + year2Deposit + year3Deposit);
            this.year3TotalProfit = this.year1Profit + this.year2Profit + this.year3Profit;
        }
        return this;
    }

    public double getYear1Profit() {
        return year1Profit;
    }

    public void setYear1Profit(double year1Profit) {
        this.year1Profit = year1Profit;
    }

    public double getYear2Profit() {
        return year2Profit;
    }

    public void setYear2Profit(double year2Profit) {
        this.year2Profit = year2Profit;
    }

    public double getYear3Profit() {
        return year3Profit;
    }

    public void setYear3Profit(double year3Profit) {
        this.year3Profit = year3Profit;
    }

    public double getYear1TotalProfit() {
        return year1TotalProfit;
    }

    public void setYear1TotalProfit(double year1TotalProfit) {
        this.year1TotalProfit = year1TotalProfit;
    }

    public double getYear2TotalProfit() {
        return year2TotalProfit;
    }

    public void setYear2TotalProfit(double year2TotalProfit) {
        this.year2TotalProfit = year2TotalProfit;
    }

    public double getYear3TotalProfit() {
        return year3TotalProfit;
    }

    public void setYear3TotalProfit(double year3TotalProfit) {
        this.year3TotalProfit = year3TotalProfit;
    }

    public void display() {
        String message = "<html><body>"
                         + "<h3>Amount invested in first year : </h3>" + year1Deposit
                         + "<h3>Profit in first year : </h3>" + year1Profit
                         + "<h3>Total Profit in first year : </h3>" + year1TotalProfit
                         + "<h3>Amount invested in second year : </h3>" + year2Deposit
                         + "<h3>Profit in second year : </p>" + year2Profit
                         + "<h3>Total Profit till second year : </h3>" + year2TotalProfit
                         + "<h3>Amount invested in third year : </h3>" + year3Deposit
                         + "<h3>Profit in third year : </h3>" + year3Profit
                         + "<h3>Total Profit till third year : </h3>" + year3TotalProfit
                         + "</body></html>";
        JOptionPane.showMessageDialog(null,message);
    }
}