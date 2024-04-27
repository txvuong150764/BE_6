import java.util.Arrays;

public class AlternatingSum {
    public static int solution(int[] A) {
        int ans = 0;
        int[] abstractArray = new int[A.length - 1];
        int sum = 0;

        for(int i = 0; i < abstractArray.length; i++) {
            abstractArray[i] = A[i + 1] - A[i];
            sum += abstractArray[i];
            ans = Math.max(ans, sum);
            if(sum < 0) {
                sum = 0;
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] A = {1892, 10000, 6758, 1750, 5367, 3125, 10000};
        System.out.println(solution(A));
    }
}
