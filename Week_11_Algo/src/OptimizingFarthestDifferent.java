import java.util.HashMap;

public class OptimizingFarthestDifferent {
    public static int solution(int[] a) {
        int ans = -1;
        HashMap<Integer, int[]> pos = new HashMap<>();

        for(int i = 0; i < a.length; i++) {
            if(!pos.containsKey(a[i])) {
                pos.put(a[i], new int[] {i, 1});
            }
            else {
                ans = Math.max(ans, i - pos.get(a[i])[0] - 1);
                pos.put(a[i], new int[] {i,  pos.get(a[i])[1] + 1});
            }
        }

        for(int key : pos.keySet()) {
            if(pos.get(key)[1] == 1) {
                ans = Math.max(ans, a.length - pos.get(key)[1]);
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] a = new int[75001];
        for(int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        System.out.println(solution(a));
    }
}
