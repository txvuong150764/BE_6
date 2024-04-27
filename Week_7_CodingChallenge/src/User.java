import java.time.LocalDate;
import java.util.Date;

public class User {
    private String name;
    private String phoneNumber;
    private LocalDate DOB;

    public User(String name, String phoneNumber, LocalDate DOB) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDOB() {
        return DOB;
    }
}
