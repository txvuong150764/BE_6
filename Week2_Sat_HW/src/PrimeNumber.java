import java.util.Scanner;

public class PrimeNumber {
    public static boolean isPrimeNumber(int number) {
        if(number <= 1)
            return false;

        for(int i = 2; i*i <= number; i++) {
            if(number % i == 0)
                return false;
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        System.out.println(isPrimeNumber(number));
    }
}
