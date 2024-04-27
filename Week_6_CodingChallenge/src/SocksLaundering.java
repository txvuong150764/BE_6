import java.util.HashMap;

public class SocksLaundering {
    public static HashMap<Integer, Integer> createHashMap(int[] arr) {
        HashMap<Integer, Integer> ans = new HashMap<>();

        for(int i : arr) {
            if(!ans.containsKey(i)) {
                ans.put(i, 1);
            }
            else {
                ans.put(i, ans.get(i) + 1);
            }
        }

        return ans;
    }
    public static int solution(int K, int[] C, int[] D) {
        int ans = 0;
        HashMap<Integer, Integer> clean = createHashMap(C);
        HashMap<Integer, Integer> dirty = createHashMap(D);

        for(Integer key : clean.keySet()) {
            if(clean.get(key) % 2 == 1 && dirty.containsKey(key) && dirty.get(key) > 0 && K > 0) {
                clean.put(key, clean.get(key) + 1);
                dirty.put(key, dirty.get(key) - 1);
                K--;
            }
            ans += clean.get(key) / 2;
        }

        K /= 2;

        for(Integer key : dirty.keySet()) {
            ans += Math.min(Math.max(0, K), dirty.get(key) / 2);
            K -= Math.min(Math.max(0, K), dirty.get(key) / 2);
        }

        return ans;
    }
    public static void main(String[] args) {
        int K = 3;
        int[] C = {1, 2, 1, 1, 3};
        int[] D = {1, 4, 3, 2, 4};
        System.out.println(solution(K, C, D));
    }
}