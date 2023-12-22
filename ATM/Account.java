import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {
    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0.0;
    private double savingBalance = 0.0;
    Scanner input;
    DecimalFormat moneyFormat;

    public Account() {
        this.input = new Scanner(System.in);
        this.moneyFormat = new DecimalFormat("'$'###,##0.00");
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public int getPinNumber() {
        return this.pinNumber;
    }

    public double getCheckingBalance() {
        return this.checkingBalance;
    }

    public double getSavingBalance() {
        return this.savingBalance;
    }

    public void calcCheckingWithdraw(double amount) {
        this.checkingBalance -= amount;
    }

    public void calcSavingWithdraw(double amount) {
        this.savingBalance -= amount;
    }

    public void calcCheckingDeposit(double amount) {
        this.checkingBalance += amount;
    }

    public void calcSavingDeposit(double amount) {
        this.savingBalance += amount;
    }

    public void getCheckingWithdrawInput() {
        System.out.println("Checking Account balance: " + this.moneyFormat.format(this.checkingBalance));
        System.out.print("Amount you want to withdraw from Checking Account: ");
        double amount = this.input.nextDouble();
        if (this.checkingBalance - amount >= 0.0) {
            this.calcCheckingWithdraw(amount);
            System.out.println("New Checking Account Balance: " + this.moneyFormat.format(this.checkingBalance));
        } else {
            System.out.println("Not Enough Money to Withdraw");
        }

    }

    public void getSavingWithdrawInput() {
        System.out.println("Saving Account balance: " + this.moneyFormat.format(this.savingBalance));
        System.out.print("Amount you want to withdraw from Saving Account: ");
        double amount = this.input.nextDouble();
        if (this.savingBalance - amount >= 0.0) {
            this.calcSavingWithdraw(amount);
            System.out.println("New Saving Account Balance: " + this.moneyFormat.format(this.savingBalance));
        } else {
            System.out.println("Not Enough Money to Withdraw");
        }

    }

    public void getCheckingDepositInput() {
        System.out.println("Checking Account Balance: " + this.moneyFormat.format(this.checkingBalance));
        System.out.print("Amount you want to deposit to Checking Account: ");
        double amount = this.input.nextDouble();
        if (this.checkingBalance + amount >= 0.0) {
            this.calcCheckingDeposit(amount);
            System.out.println("New Checking Account Balance: " + this.moneyFormat.format(this.checkingBalance));
        } else {
            System.out.println("No Money to Deposit");
        }

    }

    public void getSavingDepositInput() {
        System.out.println("Saving Account Balance: " + this.moneyFormat.format(this.savingBalance));
        System.out.print("Amount you want to deposit to Saving Account: ");
        double amount = this.input.nextDouble();
        if (this.checkingBalance + amount >= 0.0) {
            this.calcSavingDeposit(amount);
            System.out.println("New Saving Account Balance: " + this.moneyFormat.format(this.savingBalance));
        } else {
            System.out.println("No Money to Deposit");
        }

    }
}
