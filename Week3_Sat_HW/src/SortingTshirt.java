public class SortingTshirt {
    public static String addCharToString(String S, char c, int replay) {
        for(int i = 0; i < replay; i++) {
            S += c;
        }
        return S;
    }
    public static String solution(String S) {
        String ans = "";
        int[] cnt = new int[3];

        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == 'S') {
                cnt[0]++;
            }
            else if(S.charAt(i) == 'M'){
                cnt[1]++;
            }
            else{
                cnt[2]++;
            }
        }

        ans = addCharToString(ans, 'S', cnt[0]);
        ans = addCharToString(ans, 'M', cnt[1]);
        ans = addCharToString(ans, 'L', cnt[2]);

        return ans;
    }
    public static void main(String[] args) {
        String S = "SMSMSMLSS";
        System.out.println(solution(S));
    }
}
