import java.util.Arrays;

public class DistinctArrayGenerator {
    public static int[] solution(int N) {
        int[] ans = new int[N];

        if(N % 2 != 0)
            ans[N / 2] = 0;

        for(int i = 0; i < N/2; i++) {
            ans[i] = i + 1;
            ans[N - 1 - i] = -(i + 1);
        }

        return ans;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4)));
    }
}