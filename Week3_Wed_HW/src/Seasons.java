public class Seasons {
    public static int maxAmplitude(int[] T, int start, int end) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = start; i < end; i++) {
            max = Math.max(max, T[i]);
            min = Math.min(min, T[i]);
        }

        return Math.abs(max - min);
    }
    public static String solution(int[] T) {
        String[] seasons = {"WINTER", "SPRING", "SUMMER", "AUTUMN"};
        int cnt = 0;
        int ans = -1;
        int maxAmplitude = -1;
        while(cnt < 4) {
            int seasonMax = maxAmplitude(T, cnt * T.length /4, (cnt + 1) * T.length /4);
            if(seasonMax > maxAmplitude) {
                ans = cnt;
                maxAmplitude = seasonMax;
            }
            cnt++;
        }

        return seasons[ans];
    }

    public static void main(String[] args) {
        int[] T = {2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18};
        System.out.println(solution(T));
    }
}