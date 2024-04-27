import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void showCoursesList(ArrayList<Course> courses) {
        System.out.println("\nCourse List:");
        int index = 1;
        for(Course course : courses) {
            System.out.println(index + ". " + course.getName());
            index++;
        }
        System.out.println(index + ". Exit \n");
    }

    public static void showCourseMenu() {
        System.out.println("\n1. Show All Students");
        System.out.println("2. Register New Student");
        System.out.println("3. Withdrawn");
        System.out.println("4. Back to Course List \n");
    }

    public static void userInput() {
        Scanner sc = new Scanner(System.in);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        courses.add(new Course("BE 6", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 9, 3), "Mentor 1", new ArrayList<Student>()));
        courses.add(new Course("Data 7", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 10, 3), "Mentor 2", new ArrayList<Student>()));
        courses.add(new Course("FE 1", LocalDate.of(2024, 2, 14), LocalDate.of(2024, 8, 3), "Mentor 3", new ArrayList<Student>()));
        courses.add(new Course("Full-Stack 1", LocalDate.of(2024, 5, 1), LocalDate.of(2025, 1, 3), "Mentor 4", new ArrayList<Student>()));

        while(true) {
            showCoursesList(courses);
            System.out.print("Please choose course: ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1, 2, 3, 4 -> {
                    boolean isBreak = false;
                    while(!isBreak) {
                        courses.get(option - 1).showCourse();
                        showCourseMenu();
                        System.out.print("Please choose action: ");
                        int userChoice = sc.nextInt();
                        sc.nextLine();
                        switch (userChoice) {
                            case 1 -> courses.get(option - 1).showStudents();
                            case 2 -> {
                                System.out.print("Please enter student name: ");
                                String nameToAdd = sc.nextLine();
                                System.out.print("Please enter student id: ");
                                String idToAdd = sc.nextLine();
                                System.out.print("Please enter student phone number: ");
                                String phoneToAdd = sc.nextLine();
                                courses.get(option - 1).addStudent(new Student(nameToAdd, idToAdd, phoneToAdd));
                            }
                            case 3 -> {
                                System.out.print("Please enter student id: ");
                                String idToRemove = sc.nextLine();
                                courses.get(option - 1).removeStudent(idToRemove);
                            }
                            case 4 -> isBreak = true;
                            default -> {
                            }
                        }
                    }
                }
                case 5 -> {
                    System.exit(0);
                }
            }
        }

    }
}