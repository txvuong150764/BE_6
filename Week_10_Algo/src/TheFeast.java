public class TheFeast {
    public static int theFeast(int n, int c, int m) {
        int ans = n / c;

        int wrapper = n / c;

        while(wrapper >= m) {
            ans += wrapper / m;
            wrapper = wrapper / m + wrapper % m;
        }

        return ans;
    }
    public static void main(String[] args) {
        System.out.println(theFeast(6,2,2));
    }
}
