import java.util.Arrays;

public class LargestEvenSum {
    public static int solution(int[] A, int K) {
        int ans = 0;
        int maxOdd = Integer.MIN_VALUE;
        int maxEven = Integer.MIN_VALUE;
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        if(K > A.length)
            return -1;

        Arrays.sort(A);

        for(int i = A.length - 1; i >= A.length - K; i--) {
            ans += A[i];
            if(A[i] % 2 == 0) {
                minEven = Math.min(minEven, A[i]);
            }
            else {
                minOdd = Math.min(minOdd, A[i]);
            }
        }

        if(ans % 2 == 0)
            return ans;

        for(int i = 0; i < A.length - K; i++) {
            if(A[i] % 2 == 0) {
                maxEven = Math.max(maxEven, A[i]);
            }
            else {
                maxOdd = Math.max(maxOdd, A[i]);
            }
        }

        ans = Math.max(-1, Math.max(ans - minOdd + maxEven, ans - minEven + maxOdd));

        return ans % 2 == 0 ? ans : -1;
    }

    public static void main(String[] args) {
        int[] A = {7, 7, 7, 7, 7};
        System.out.println(solution(A, 2));
    }
}