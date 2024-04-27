import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final int REGISTER_BACKEND = 1;
    public static final int REGISTER_FRONTEND = 2;
    public static final String BACKEND_FILE_NAME = "be.txt";
    public static final String FRONTEND_FILE_NAME = "fe.csv";

    public static int registerMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Backend Class");
        System.out.println("2. Frontend Class");
        System.out.print("Please choose one class to add student: ");
        return sc.nextInt();
    }
    public static void addStudent(String fileName) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please fill in student details.");

        System.out.print("Student name: ");
        String studentName = sc.nextLine();

        System.out.print("Student phone number: ");
        String studentPhoneNumber = sc.nextLine();

        System.out.print("Student DOB: ");
        String studentDOB = sc.nextLine();

        String[] dobSplit = studentDOB.split("/", 3);

        User user = new User(studentName, studentPhoneNumber, LocalDate.of(Integer.parseInt(dobSplit[2]), Integer.parseInt(dobSplit[1]), Integer.parseInt(dobSplit[0])));

        try {
            FileWriter writer = new FileWriter(fileName, true);
            String userInfo = user.getName() + "," + user.getPhoneNumber() + "," + user.getDOB().toString() + "\n";
            writer.write(userInfo);
            System.out.println("Student was added successfully!");
            writer.close();;
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static ArrayList<User> readFile(String fileName) {
        ArrayList<User> users = new ArrayList<>();
        try {
            File myFile = new File(fileName);
            if(myFile.exists() && !myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                while(sc.hasNextLine()) {
                    String studentInfo = sc.nextLine();
                    if(!studentInfo.isEmpty()) {
                        String[] studentInfoSplit = studentInfo.split(",", 3);
                        String[] dobSplit = studentInfoSplit[2].split("-", 3);

                        User user = new User(studentInfoSplit[0], studentInfoSplit[1], LocalDate.of(Integer.parseInt(dobSplit[0]), Integer.parseInt(dobSplit[1]), Integer.parseInt(dobSplit[2])));
                        users.add(user);
                    }
                }
                sc.close();
            }
            else {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void main(String[] args) {
        while(true) {
            ArrayList<User> usersBackend = readFile(BACKEND_FILE_NAME);
            ArrayList<User> usersFrontEnd = readFile(FRONTEND_FILE_NAME);
            int registerOption = registerMenu();
            switch (registerOption) {
                case REGISTER_BACKEND -> {
                    Utils.printClass(usersBackend);
                    addStudent(BACKEND_FILE_NAME);
                }
                case REGISTER_FRONTEND -> {
                    Utils.printClass(usersFrontEnd);
                    addStudent(FRONTEND_FILE_NAME);
                }
            }
        }
    }
}