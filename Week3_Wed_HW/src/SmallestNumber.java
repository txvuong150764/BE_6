public class SmallestNumber {
    public static int solution(int[] T) {
        int len = T.length;
        boolean hasOne = false;

        for(int i = 0; i < len; i++) {
             if(T[i] == 1) {
                 hasOne = true;
             }

             if(T[i] < 0 || T[i] > len + 1) {
                 T[i] = 1;
             }
        }

        if(!hasOne) {
            return 1;
        }

        for(int i = 0; i < len; i++) {
            T[(T[i] - 1) % len] += len;
        }

        for(int i = 0; i < len; i++) {
            if(T[i] < len) {
                return i + 1;
            }
        }

        return len + 1;
    }
    public static void main(String[] args){
        int[] T = {-1, -3, 4, 2, 1};
        System.out.println(solution(T));
    }
}
