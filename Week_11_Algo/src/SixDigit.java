import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SixDigit {
    public static int calculateTime(int[] perm) {
        int second = perm[5] + 10 * perm[4];
        int minute = perm[3] + 10 * perm[2];
        int hour = perm[1] + 10 * perm[0];

        if(second > 60 || minute > 60 || hour > 23) {
            return Integer.MAX_VALUE;
        }

        return hour * 3600 + minute * 60 + second;
    }
    public static String solution(int a, int b, int c, int d, int e, int f) {
        int[] arr = {a, b, c, d, e, f};
        String ans = "";
        int minTimeValue = Integer.MAX_VALUE;
        for(int i1 = 0; i1 < 6; i1++) {
            for(int i2 = 0; i2 < 6; i2++) {
                for(int i3 = 0; i3 < 6; i3++) {
                    for(int i4 = 0; i4 < 6; i4++) {
                        for(int i5 = 0; i5 < 6; i5++) {
                            if(i1 == i2 || i2 == i3 || i3 == i4 || i4 == i5 || i1 == i3 || i1 == i4 || i1 == i5 || i2 == i4 || i2 == i5 || i3 == i5)
                                continue;
                            int i6 = 15 - i1 - i2 - i3 - i4 - i5;
                            int[] perm = {arr[i1], arr[i2], arr[i3], arr[i4], arr[i5], arr[i6]};
                            int timeValue = calculateTime(perm);
                            minTimeValue = Math.min(timeValue, minTimeValue);
                        }
                    }
                }
            }
        }

        if(minTimeValue == Integer.MAX_VALUE) {
            return "NOT POSSIBLE";
        }
        else {
            int hour = minTimeValue / 3600;
            int minute = minTimeValue - 3600 * hour;
            return String.format("%02d:%02d:%02d", hour, minute / 60, minute % 60);
        }

    }
    public static void main(String[] args) {
        System.out.println(solution(2,4,5,9,5,9));
    }
}
