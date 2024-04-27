import java.util.Scanner;

public class Main {
    public static char scoreToGrade(float score) {
        if (score < 5) {
            return 'F';
        }
        else if (score >= 5 && score < 6) {
            return  'D';
        }
        else if (score >= 6 && score < 7) {
            return  'C';
        }
        else if (score >= 7 && score < 8) {
            return  'B';
        }
        else if (score >= 8 && score < 9) {
            return  'A';
        }
        else  {
            return  '@';
        }
    }
    public static char findGrade(float averageScore, float minScore) {
        if (scoreToGrade(minScore) == 'F') {
            return 'F';
        }

        // C - A = 2
        if (scoreToGrade(minScore) - scoreToGrade(averageScore) > 1) {
            return (char) (scoreToGrade(minScore) - 1);
        }

        return (char) scoreToGrade(averageScore);
    }

    public static void printAdvice(char finalGrade) {
        switch (finalGrade) {
            case '@' -> System.out.println("Học sinh xuất sắc!!!");
            case 'A' -> System.out.println("Học sinh giỏi!!!");
            case 'B' -> System.out.println("Học sinh khá!!!");
            case 'C' -> System.out.println("Học sinh trung bình!!!");
            case 'D' -> System.out.println("Học sinh ngấp nghé rớt!!!");
            case 'F' -> System.out.println("Học sinh rớt!!!");
            default -> {
            }
        }
    }

    public static void main(String[] args) {
        float minScore = 11;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập điểm môn Toán: ");
        float mathScore = sc.nextFloat();
        minScore = Math.min(mathScore, minScore);

        System.out.println("Nhập điểm môn Văn: ");
        float literatureScore = sc.nextFloat();
        minScore = Math.min(literatureScore, minScore);

        System.out.println("Nhập điểm môn Tiếng Anh: ");
        float englishScore = sc.nextFloat();
        minScore = Math.min(englishScore, minScore);

        float averageScore = (mathScore + literatureScore + englishScore) / 3;

        char finalGrade = findGrade(averageScore, minScore);

        if(finalGrade == '@')
            finalGrade = 'S';

        System.out.println("Điểm môn Toán: " + mathScore);
        System.out.println("Điểm môn Văn: " + literatureScore);
        System.out.println("Điểm môn Tiếng Anh: " + englishScore);
        System.out.printf("Điểm trung bình các môn học: %.2f\n", averageScore);
        System.out.println("Xếp loại: " + finalGrade);
        printAdvice(finalGrade);
    }
}