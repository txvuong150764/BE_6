import java.util.Arrays;
import java.util.HashMap;

public class OptimizingFarthestDifferent {
    public static int firstSolution(int[] a) {
        int ans = -1;
        HashMap<Integer, int[]> pos = new HashMap<>();

        for(int i = 0; i < a.length; i++) {
            if(!pos.containsKey(a[i])) {
                pos.put(a[i], new int[] {i, 1});
            }
            else if(a[i - 1] != a[i]){
                ans = Math.max(ans, i - pos.get(a[i])[0] - 1);
                pos.put(a[i], new int[]{pos.get(a[i])[0], pos.get(a[i])[1] + 1});
            }
        }
        for(int key : pos.keySet()) {
            if(pos.get(key)[1] == 1) {
                ans = Math.max(ans, a.length - pos.get(key)[0]);
            }
        }

        return ans;
    }

    public static int secondSolution(int[] a) {
        int ans = 0;

        for (int i = 0; i < a.length; ++i) {
            if (a[i] != a[a.length - 1] || a[0] != a[a.length - 1 - i]) {
                ans = a.length - i - 1;
                return ans;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] a = {4, 6, 2, 2, 6, 6, 4};
        System.out.println(firstSolution(a));
    }
}
