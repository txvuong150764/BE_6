import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Course {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String mentor;
    private ArrayList<Student> students;
    public Course(String name, LocalDate startDate, LocalDate endDate, String mentor, ArrayList<Student> students) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mentor = mentor;
        this.students = students;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public String getMentor() {
        return mentor;
    }
    public void setMentor(String mentor) {
        this.mentor = mentor;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public int getNumOfStudents() {
        return this.students.size();
    }
    public void showCourse() {
        System.out.println("\nCourse name: " + this.name);
        System.out.println("Duration: " + this.startDate + " -> " + this.endDate);
        System.out.println("Mentor: " + this.mentor);
        if(this.students.size() > 1)
            System.out.println("Registered Students: " + this.students.size() + " students");
        else {
            System.out.println("Registered Students: " + this.students.size() + " student");
        }
    }
    public int checkExistStudent(String id) {
        for(int i = 0; i < this.students.size(); i++) {
            if(id.equals(students.get(i).getID()))
                return i;
        }
        return -1;
    }
    public void addStudent(Student newStudent) {
        if(checkExistStudent(newStudent.getID()) == -1)
            this.students.add(newStudent);
        else {
            System.out.println("Student ID already existed. Please use another ID to register!!!!");
        }
    }
    public void removeStudent(String id) {
        int pos = checkExistStudent(id);
        if(pos != -1) {
            this.students.remove(pos);
        }
        else {
            System.out.println("Student with ID: " + id + " dose not exist. Please try other action!!!!");
        }
    }
    public void showStudents() {
        if(students.isEmpty()) {
            System.out.println("There is no registered student in this course.");
            return;
        }
        for(Student student : students) {
            System.out.print(student.getName() + " | ");
        }
        System.out.println();
    }
}
