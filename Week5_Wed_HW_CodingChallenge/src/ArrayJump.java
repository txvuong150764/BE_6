public class ArrayJump {
    public static int solution(int[] A) {
        int slow = 0;
        int fast = 0;
        int ans = 0;
        while(slow > -1 && slow < A.length && fast > -1 && fast < A.length) {
            if(ans % 2 == 1){
                slow = slow + A[slow];
            }
            fast = fast + A[fast];
            if(slow == fast) {
                return -1;
            }
            ans++;
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] A = {3, 3, -1, 1, 3};
        System.out.println(solution(A));
    }
}