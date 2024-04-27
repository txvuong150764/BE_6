import java.util.Arrays;

public class SortArray {
    public static int[] sort(int[] arr) {
        int[] ans = new int[arr.length];
        int[] cnt = new int[3];

        for(int i : arr) {
            cnt[i] += 1;
        }

        int i = 0, j = 0;
        while(j < 3) {
            ans[i++] = j;
            cnt[j]--;
            if(cnt[j] == 0)
                j++;
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 0, 1, 2, 2};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
