public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        for(int i = 0; i < strs[0].length(); i++) {
            for(String s : strs) {
                if(i >= s.length() || s.charAt(i) != strs[0].charAt(i))
                    return strs[0].substring(0, i);
            }
        }

        return strs[0];
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flight", "flow"};
        System.out.println(longestCommonPrefix(strs));
    }
}
