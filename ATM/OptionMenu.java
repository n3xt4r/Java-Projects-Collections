import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class OptionMenu extends Account {
    Scanner menuInput;
    DecimalFormat moneyFormat;
    HashMap<Integer, Integer> data;

    public OptionMenu() {
        this.menuInput = new Scanner(System.in);
        this.moneyFormat = new DecimalFormat("'$'###,##0.00");
        this.data = new HashMap();
    }

    public void getLogin() {
        int x = 1;

        do {
            try {
                Connection conn = null;

                try {
                    String url = "jdbc:postgresql://localhost:5432/atm";
                    String user = "learnjava_my_atm";
                    String password = "atm123";
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                } catch (ClassNotFoundException | SQLException var7) {
                    System.out.println("Connection failed: " + var7.getMessage());
                }

                try {
                    assert conn != null;

                    Statement stmt = conn.createStatement();

                    try {
                        ResultSet rs = stmt.executeQuery("SELECT pin_nr FROM atm WHERE customer_account_nr = " + this.getCustomerNumber());

                        try {
                            if (rs.next()) {
                                int pn = rs.getInt("pin_nr");
                                if (pn == this.getPinNumber()) {
                                    this.getAccountType();
                                } else {
                                    System.out.println("\nWrong PIN Number\n\n");
                                }
                            } else {
                                System.out.println("\nWelcome\n\n");
                            }
                        } catch (Throwable var11) {
                            if (rs != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var10) {
                                    var11.addSuppressed(var10);
                                }
                            }

                            throw var11;
                        }

                        if (rs != null) {
                            rs.close();
                        }
                    } catch (Throwable var12) {
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var9) {
                                var12.addSuppressed(var9);
                            }
                        }

                        throw var12;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException var13) {
                    System.out.println("Error retrieving data: " + var13.getMessage());
                }

                try {
                    conn.close();
                } catch (SQLException var8) {
                    System.out.println("Error closing connection: " + var8.getMessage());
                }

                System.out.println("Welcome to ATM");
                System.out.println("Enter your Customer Number");
                this.setCustomerNumber(this.menuInput.nextInt());
                System.out.println("Enter your PIN Number");
                this.setPinNumber(this.menuInput.nextInt());
            } catch (Exception var14) {
                System.out.println("\nInvalid Characters Only Numbers Allowed\n" + String.valueOf(var14));
                x = 2;
            }

            int cn = this.getCustomerNumber();
            int pn = this.getPinNumber();
            if (this.data.containsKey(cn) && (Integer)this.data.get(cn) == pn) {
                this.getAccountType();
            } else {
                System.out.println("\nWrong Customer Number or Wrong PIN Number\n\n");
            }
        } while(x == 1);

    }

    public void getAccountType() {
        System.out.println("Select Account Type you want to Access");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Savings Account");
        System.out.println("Type 3 - Exit");
        int selection = this.menuInput.nextInt();
        switch (selection) {
            case 1:
                this.getChecking();
                break;
            case 2:
                this.getSaving();
                break;
            case 3:
                System.out.println("Thank you for using ATM, BYE\n");
                break;
            default:
                System.out.println("\n Invalid Choice \n");
        }

    }

    public void getChecking() {
        System.out.println("Checking Account");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Money");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        int selection = this.menuInput.nextInt();
        switch (selection) {
            case 1:
                DecimalFormat var10001 = this.moneyFormat;
                System.out.println("Checking Account Balance: " + var10001.format(this.getCheckingBalance()));
                this.getAccountType();
                break;
            case 2:
                this.getCheckingWithdrawInput();
                this.getAccountType();
                break;
            case 3:
                this.getCheckingDepositInput();
                this.getAccountType();
                break;
            case 4:
                System.out.println("Thank you for using ATM, Bye");
                break;
            default:
                System.out.println("\nInvalid Choice\n");
                this.getChecking();
        }

    }

    public void getSaving() {
        System.out.println("Saving Account");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Money");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");
        int selection = this.menuInput.nextInt();
        switch (selection) {
            case 1:
                DecimalFormat var10001 = this.moneyFormat;
                System.out.println("Saving Account Balance: " + var10001.format(this.getSavingBalance()));
                this.getAccountType();
                break;
            case 2:
                this.getSavingWithdrawInput();
                this.getAccountType();
                break;
            case 3:
                this.getSavingDepositInput();
                this.getAccountType();
                break;
            case 4:
                System.out.println("Thank you for using ATM, Bye\n");
                break;
            default:
                System.out.println("\nInvalid Choice\n");
                this.getChecking();
        }

    }
}
