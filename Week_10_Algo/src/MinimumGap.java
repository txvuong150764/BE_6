import java.util.HashMap;

public class MinimumGap {
    public static int minimumGap(int[] a) {
        int ans = Integer.MAX_VALUE;

        HashMap<Integer, Integer> tmp = new HashMap<>();

        for(int i = 0; i < a.length; i++) {
            if(!tmp.containsKey(a[i])) {
                tmp.put(a[i], i);
            }
            else {
                ans = Math.min(ans, i - tmp.get(a[i]));
                tmp.put(a[i], i);
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] a = {7, 1, 3, 4, 1, 1, 7};
        System.out.println(minimumGap(a));
    }
}
