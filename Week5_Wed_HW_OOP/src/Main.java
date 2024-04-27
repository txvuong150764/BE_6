import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<User> users = new ArrayList<>();
    public static final int VIEW_BALANCE = 1;
    public static final int DEPOSIT = 2;
    public static final int WITHDRAW = 3;
    public static final int TRANSFER = 4;
    public static final int LOG_OUT = 5;
    public static User getReceiveUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Card No of Receiver: ");
        String cardNo = sc.nextLine();

        for(User user : users) {
            if(user.getCardNo().equals(cardNo)) {
                return user;
            }
        }

        return null;
    }
    public static User getUser(String cardNo, String PIN) {
        for(User user : users) {
            if(user.getCardNo().equals(cardNo) && user.getPIN().equals(PIN)) {
                return user;
            }
        }
        return null;
    }
    public static int mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------- Main Menu ----------------------------");
        System.out.println("                          1. View Balance                           ");
        System.out.println("                          2. Deposit                           ");
        System.out.println("                          3. Withdraw                           ");
        System.out.println("                          4. Transfer                           ");
        System.out.println("                          5. Log Out                           ");
        System.out.print("Please choose 1 action: ");

        return sc.nextInt();
    }
    public static User login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------- Login Screen ----------------------------");
        System.out.print("                      Card No: ");
        String cardNo = sc.nextLine();
        System.out.print("                      PIN    : ");
        String PIN = sc.nextLine();

        User loginUser = getUser(cardNo, PIN);
        if(loginUser != null) {
            System.out.println("\n                        Login Successfully!!!!");
        }
        else {
            System.out.println("\nCard No or PIN is incorrect. Please try again!!!!!!!!");
            loginUser = login();
        }
        return loginUser;
    }
    public static void transfer(User loginUser) {
        Scanner sc = new Scanner(System.in);

        User receiver = getReceiveUser();
        if(receiver != null) {
            System.out.print("Enter the amount to transfer: ");
            int amount = sc.nextInt();
            loginUser.transfer(amount, receiver);
        }
        else {
            System.out.println("The person you want to transfer money dose not exist");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        users.add(new User("123456", "1234", new Account()));
        users.add(new User("150720", "3214", new Account()));
        users.add(new User("010120", "5354", new Account()));
        users.add(new User("987654", "8567", new Account()));
        users.add(new User("135791", "1231", new Account()));

        User loginUser = login();
        while(true) {
            int option = mainMenu();
            switch (option) {
                case VIEW_BALANCE -> {
                    loginUser.viewBalance();
                }
                case DEPOSIT -> {
                    System.out.print("Enter the amount to deposit: ");
                    loginUser.deposit(sc.nextInt());
                }
                case WITHDRAW -> {
                    System.out.print("Enter the amount to withdraw: ");
                    loginUser.withdraw(sc.nextInt());
                }
                case TRANSFER -> {
                    transfer(loginUser);
                }
                case LOG_OUT -> {
                    loginUser = login();
                }
                default -> System.exit(0);
            }
        }
    }
}