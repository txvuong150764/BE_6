public class Palindrome {
    public static boolean isPalindrome(int[] numbers) {
        for(int i = 0; i < numbers.length / 2; i++) {
            if(numbers[i] != numbers[numbers.length - 1 - i])
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1};
        System.out.println(isPalindrome(numbers));
    }
}