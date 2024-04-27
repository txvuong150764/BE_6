import java.util.ArrayList;

public class Utils {
    public static final int USER_NAME_WIDTH_DEFAULT = 9;
    public static final int PHONE_NUMBER_WIDTH_DEFAULT = 12;
    public static final int DOB_WIDTH_DEFAULT = 13;
    public static final int CLASS_TITTLE = 13;
    public static void printHeader(int nameSize, int phoneSize, int dobSize) {
        printSingleLine(nameSize, phoneSize, dobSize);
        System.out.format("| %-" + nameSize + "s | %-" + phoneSize + "s | %-" + dobSize + "s |%n", "User Name", "Phone Number", "Date of Birth");
        printSingleLine(nameSize, phoneSize, dobSize);
    }

    public static void printUser(int nameSize, int phoneSize, int dobSize, User user) {
        System.out.format("| %-" + nameSize + "s | %-" + phoneSize + "s | %-" + dobSize + "s |%n", user.getName(), user.getPhoneNumber(), user.getDOB());
    }

    public static void printSingleLine(int nameSize, int phoneSize, int dobSize) {
        for(int i = 0; i <= nameSize + phoneSize + dobSize + 9; i++) {
            System.out.print("+");
        }
        System.out.print("\n");
    }

    public static void printClassHeader(int nameSize, int phoneSize, int dobSize) {
        for(int i = 0; i <= (nameSize + phoneSize + dobSize + 9) / 2 - CLASS_TITTLE / 2; i++) {
            System.out.print(" ");
        }
        System.out.print("Student List\n");
    }

    public static void printClass(ArrayList<User> users) {
        int nameSize = USER_NAME_WIDTH_DEFAULT;
        int phoneSize = PHONE_NUMBER_WIDTH_DEFAULT;

        for(User user : users) {
            nameSize = Math.max(USER_NAME_WIDTH_DEFAULT, user.getName().length());
            phoneSize = Math.max(PHONE_NUMBER_WIDTH_DEFAULT, user.getPhoneNumber().length());
        }

        printClassHeader(nameSize, phoneSize, DOB_WIDTH_DEFAULT);
        printHeader(nameSize, phoneSize, DOB_WIDTH_DEFAULT);
        for(User user : users) {
            printUser(nameSize, phoneSize, DOB_WIDTH_DEFAULT, user);
        }
        printSingleLine(nameSize, phoneSize, DOB_WIDTH_DEFAULT);
    }
}
