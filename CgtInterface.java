package src;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CgtInterface {
    private static User[] users;
    private static int noUsers;
    static Scanner s;

    public CgtInterface() {
        users = new User[5];
        s = new Scanner(System.in);
        noUsers = -1;
    }

    public static void main(String[] args) throws Exception {
        CgtInterface cgtInterface = new CgtInterface();
        while (true) {
            String message = "<html><body>"
                             + "<p>Enter the option from below menu</p>"
                             + "<p>1:Add User</p>"
                             + "<p>2:Delete User</p>"
                             + "<p>3:Display User</p>"
                             + "<p>4:Display All Users</p>"
                             + "<p>5:Add Investment</p>"
                             + "<p>6:Display Investment</p>"
                             + "<p>7:Delete Investment</p>"
                             + "<p>8:Save to file</p>"
                             + "<p>9:Exit</p>"
                             + "</body></html>";
            int choice = Integer.parseInt(JOptionPane.showInputDialog(null, message));

            switch (choice) {
                case 1 -> {
                    addUser();
                }
                case 2 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");
                        continue;
                    }
                    deleteUser();
                }
                case 3 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");
                        continue;
                    }
                    displayUser();
                }
                case 4 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");
                        continue;
                    }
                    displayAllUsers();
                }
                case 5 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");
                        continue;
                    }
                    addInvestment();
                }
                case 6 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");
                        continue;
                    }
                    displayInvestment();
                }
                case 7 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");
                        continue;
                    }
                    deleteInvestment();
                }
                case 8 -> {
                    if (noUsers == -1) {
                        JOptionPane.showMessageDialog(null,"No users found");

                        continue;
                    }
                    saveInFile();
                }
                case 9 -> {
                    System.exit(0);
                }
                default -> {
                    throw new Exception("Please enter valid Option");
                }
            }
        }
    }

    /**
     * saveInFile
     */
    private static void saveInFile() {
        try {
            File file = new File("C:\\Users\\User\\Desktop\\file.dat");
            OutputStream os = new FileOutputStream(file);
            // Starting writing the bytes in it
            os.write(users[0].toString().getBytes(StandardCharsets.UTF_8));
            os.close();
            JOptionPane.showMessageDialog(null,"The users have successfully been written to a file");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"File could not be saved.");
        }
    }


    /**
     * deleteInvestment
     */
    private static void deleteInvestment() {
        String name = JOptionPane.showInputDialog("Enter name of the user");
        for (int i = 0; i <= noUsers; i++) {
            if (users[i].getName().equals(name)) {
                if (users[i].getInvestments() == null) {
                    JOptionPane.showMessageDialog(null,"No investments found.");
                } else {
                    int investmentNo = Integer.parseInt(JOptionPane.showInputDialog("Enter name of the user"));
                    investmentNo--;
                    if (investmentNo == 0 && users[i].getInvestments()[investmentNo] == null) {
                        users[i].setInvestments(null);
                    } else if (investmentNo == 0 && users[i].getInvestments()[investmentNo] != null) {
                        users[i].getInvestments()[0] = users[i].getInvestments()[1];
                        users[i].getInvestments()[1] = null;
                    } else if (investmentNo == 1 && users[i].getInvestments()[investmentNo] != null) {
                        users[i].getInvestments()[1] = null;
                    } else if (investmentNo == 1 && users[i].getInvestments()[investmentNo] == null) {
                        JOptionPane.showMessageDialog(null,"No investment with this number.");
                    } else {
                        JOptionPane.showMessageDialog(null,"Invalid Investment number.");
                    }
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null,"No user with name found.");
    }


    /**
     * displayInvestment
     */
    private static void displayInvestment() {
        String name = JOptionPane.showInputDialog("Enter name of the user");
        for (int i = 0; i <= noUsers; i++) {
            if (users[i].getName().equals(name)) {
                if (users[i].getInvestments() != null) {
                    users[i].getInvestments()[0].display();
                    if (users[i].getInvestments()[1] != null) {
                        users[i].getInvestments()[1].display();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"No investments.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"No user with name found.");
    }

    /**
     * addInvestment
     */
    private static void addInvestment() {
        String name = JOptionPane.showInputDialog("Enter name of the user");
        for (int i = 0; i <= noUsers; i++) {
            if (users[i].getName().equals(name)) {
                if (users[i].getInvestments() == null || users[i].getInvestments()[1] == null) {
                    Investment investmentProfits = null;
                    double year1Deposit = 0.0;
                    double year2Deposit = 0.0;
                    double year3Deposit = 0.0;
                    Investment investment = new Investment(year1Deposit, year2Deposit, year3Deposit);
                    int coinSelected = 0;
                    while (true) {
                        JOptionPane.showMessageDialog(null,"Initial Investment Amount (Cannot be more than $" + users[i].getActualProfit() + ")");
                        year1Deposit = Double.parseDouble(JOptionPane.showInputDialog("Please enter year1 deposit"));
                        if (year1Deposit < users[i].getActualProfit() && year1Deposit > 0) {
                            investment.setYear1Deposit(year1Deposit);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null,"Error! Initial Year investment must be Positive no. and non-zero and less than profit.");
                        }
                    }
                    while (true) {
                        year2Deposit = Double.parseDouble(JOptionPane.showInputDialog("Please enter year2 deposit"));
                        if (year2Deposit > 0) {
                            investment.setYear2Deposit(year2Deposit);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null,"Error! Investment year must be Positive no. and non-zero.");
                        }
                    }

                    while (true) {
                        JOptionPane.showMessageDialog(null,"Investment Amount after Second Year =");
                        year3Deposit = Double.parseDouble(JOptionPane.showInputDialog("Please enter year3 deposit"));
                        if (year3Deposit > 0) {
                            investment.setYear3Deposit(year3Deposit);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null,"Error! Investment year must be Positive no. and non-zero.");
                        }
                    }

                    String message = "<html><body>"
                                     + "<h3>Choose the Cryptocurrency to invest in :</h3>"
                                     + "<h3>1 for Best Coin.</h3>"
                                     + "<h3>2 for Simple Coin.</h3>"
                                     + "<h3>3 for Fast Coin.</h3>"
                                     + "<h2>Enter the coin of your choice = </h2>"
                                     + "</body></html>";

                    while (true) {
                        coinSelected = Integer.parseInt(JOptionPane.showInputDialog(null, message));
                        if (coinSelected > 0 && coinSelected <= 3) {
                            investmentProfits = investment.calcInvestment(coinSelected, investment.getYear1Deposit(),
                                    investment.getYear2Deposit(), investment.getYear3Deposit());
                            break;
                        }
                    }
                    String coinType = null;
                    if (coinSelected == 1) {
                        coinType = "Best Coin";
                    } else if (coinSelected == 2) {
                        coinType = "Simple Coin";
                    } else {
                        coinType = "Fast Coin";
                    }
                    JOptionPane.showMessageDialog(null,"Coin type selected is : " + coinType);
                    investment.setCoinSelection(coinSelected);
                    if (users[i].getInvestments() == null) {
                        Investment[] investments = new Investment[2];
                        investments[0] = investmentProfits;
                        investments[1] = null;
                        users[i].setInvestments(investments);
                    } else {
                        Investment[] investments = users[i].getInvestments();
                        investments[1] = investmentProfits;
                        users[i].setInvestments(investments);
                    }
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,"Investment are already 2 in number");
                    return;
                }
            }
        }
    }

    private static void displayAllUsers() {
        Arrays.sort(users, 0, noUsers, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (int i = 0; i <= noUsers; i++) {
            users[i].display();
        }
    }

    private static void displayUser() {
        String name = JOptionPane.showInputDialog("Enter name of the user");
        for (int i = 0; i <= noUsers; i++) {
            if (users[i].getName().equals(name)) {
                users[i].display();
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"the user does not exist");
    }

    private static void deleteUser() {
        String name = JOptionPane.showInputDialog("Enter user name to delete");
        int index = -1;
        for (int i = 0; i <= noUsers; i++) {
            if (users[i].getName().equals(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            JOptionPane.showMessageDialog(null,"the user does not exist");
            return;
        } else if (index == noUsers) {
            JOptionPane.showMessageDialog(null,"User deleted.");
        } else {
            for (int i = index; i < noUsers; i++) {
                users[i] = users[i + 1];
            }
        }
        users[noUsers] = null;
        noUsers--;
    }

    private static void addUser() {
        if (noUsers >= 4) {
            JOptionPane.showMessageDialog(null,"it is not possible to add a user.");
            return;
        }

        noUsers++;
        String name = JOptionPane.showInputDialog("Enter name:");

        double annualSalary;
        while (true) {
            annualSalary = Double.parseDouble(JOptionPane.showInputDialog("Enter Annual Salary:"));
            if (annualSalary <= 0) {
                JOptionPane.showMessageDialog(null,"Annual Salary should be greater than zero");
            } else {
                break;
            }
        }


        boolean isResident;
        while (true) {
            String resident = String.valueOf(JOptionPane.showInputDialog("If user is resident or not?(y/yes or n/no)"));
            resident = resident.toLowerCase();
            if (resident.equals("y") || resident.equals("yes")) {
                isResident = true;
                break;
            } else if (resident.equals("n") || resident.equals("no")) {
                isResident = false;
                break;
            } else {
                JOptionPane.showMessageDialog(null,"Enter valid option");
            }
        }

        double buyingPrice;
        while (true) {
            buyingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Buying Price"));
            if (buyingPrice <= 0) {
                JOptionPane.showMessageDialog(null,"Buying Price should be greater than zero");
            } else {
                break;
            }
        }

        double sellingPrice;
        while (true) {
            sellingPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Selling Price"));
            if (sellingPrice <= 0) {
                JOptionPane.showMessageDialog(null,"Selling Price should be greater than zero");
            } else if (sellingPrice <= buyingPrice) {
                JOptionPane.showMessageDialog(null,"Selling Price should be more than buying price");
            } else {
                break;
            }
        }

        int years;
        while (true) {
            years = Integer.parseInt(JOptionPane.showInputDialog("Enter No of years cryptocurrency is held"));
            if (years <= 0) {
                JOptionPane.showMessageDialog(null,"No of years cryptocurrency is held should be greater than zero");
            } else {
                break;
            }
        }

        users[noUsers] = new User(name, annualSalary, buyingPrice, sellingPrice, years, isResident);
        }
    }
