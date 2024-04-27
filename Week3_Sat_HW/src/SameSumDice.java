import java.util.Arrays;

public class SameSumDice {
    public static int solution(int[] A, int[] B) {
        int[] cnt = new int[6];
        int sumA = 0;
        int sumB = 0;
        int ans = 0;

        if(A.length - 6 * B.length > 0 || B.length - 6 * A.length > 0)
            return -1;

        for(int num : A) {
            sumA += num;
        }

        for(int num : B) {
            sumB += num;
        }

        if(sumA > sumB) {
            int[] tmp = A;
            A = B;
            B = tmp;
        }

        int diff = Math.abs(sumA - sumB);

        for(int num : A) {
            cnt[6 - num]++;
        }

        for(int num : B) {
            cnt[num - 1]++;
        }

        int j = 5;
        while(diff > 0 && j > 0) {
            if(cnt[j] > 0) {
                ans += (int) Math.min(cnt[j], Math.ceil((double) diff / j));
                diff -= cnt[j] * j;
                if(diff <= 0)
                    return ans;
            }
            j--;
        }

        return ans;
    }
    public static void  main(String[] args) {
        int[] A = {5, 6, 6};
        int[] B = {1, 1, 6};

        System.out.println(solution(A, B));
    }
}
