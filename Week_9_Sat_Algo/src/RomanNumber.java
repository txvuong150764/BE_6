import java.util.HashMap;

public class RomanNumber {
    public static int romanToInt(String s) {
        int ans = 0;

        HashMap<Character, Integer> romanValue = new HashMap<>();
        romanValue.put('I', 1);
        romanValue.put('V', 5);
        romanValue.put('X', 10);
        romanValue.put('L', 50);
        romanValue.put('C', 100);
        romanValue.put('D', 500);
        romanValue.put('M', 1000);

        for(int i = 0; i < s.length() - 1; i++) {
            if(romanValue.get(s.charAt(i+1)) > romanValue.get(s.charAt(i))) {
                ans -= romanValue.get(s.charAt(i));
            }
            else {
                ans += romanValue.get(s.charAt(i));
            }
        }

        return ans + romanValue.get(s.charAt(s.length() - 1));
    }
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }
}
