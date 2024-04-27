public class MaxProfit {
    public static int solution(int[] A) {
        int ans = 0;
        int plus = 1;

        for(int i = 0; i < A.length - 1; i++) {
            if(plus == 1 && A[i] > A[i + 1]) {
                ans += A[i] % 1000000000;
                plus = -1;
            }

            if(plus == -1 && A[i] < A[i + 1]) {
                ans -= A[i] % 1000000000;
                plus = 1;
            }
        }

        return ans + A[A.length - 1] * plus ;
    }

    public static void main(String[] args) {
        int[] A = {4, 1, 2, 1};
        System.out.println(solution(A));
    }
}
