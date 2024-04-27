public class ShoeFactory {
    public static int solution(String S) {
        int ans = 0;
        int leftShoe = 0;
        int righShoe = 0;

        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == 'L') {
                leftShoe++;
            }
            else {
                righShoe++;
            }
            if(leftShoe == righShoe) {
                ans++;
                leftShoe = 0;
                righShoe = 0;
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        String S = "RLLLRRRLLR";
        System.out.println(solution(S));
    }
}
