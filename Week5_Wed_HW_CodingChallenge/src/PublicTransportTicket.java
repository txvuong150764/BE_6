public class PublicTransportTicket {
    public static int solution(int[] A) {
        int[] price = {2, 7, 25};
        int lastDay = A[A.length - 1];
        int[] dp = new int[lastDay + 1];
        int i = 0;
        for(int day = 1; day <= lastDay; day++) {
            if(day < A[i]){
                dp[day] = dp[day - 1];
            }
            else {
                i++;
                dp[day] = Math.min(dp[day - 1] + price[0], Math.min(dp[Math.max(0, day - 7)] + price[1], dp[Math.max(0, day - 30)] + price[2]));
            }
        }

        return dp[lastDay];
    }
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 7, 8, 9, 10, 11};
        System.out.println(solution(A));
    }
}
