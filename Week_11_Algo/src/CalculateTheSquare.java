public class CalculateTheSquare {
    public static int solution(int A, int B) {
        int ans = 0;
        A = (int) Math.ceil(Math.sqrt(A));
        B = (int) Math.floor(Math.sqrt(B));
        while(A <= B) {
            System.out.println(A + " " + B);
            A = (int) Math.ceil(Math.sqrt(A));
            B = (int) Math.floor(Math.sqrt(B));
            ans++;
        }

        return ans;
    }
    public static void main(String[] args) {
        System.out.println(solution(6000, 7000));
    }
}
