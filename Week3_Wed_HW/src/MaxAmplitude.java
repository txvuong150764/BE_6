public class MaxAmplitude {
    public static int maxAmplitude(int[] T) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num : T) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        return Math.abs(max - min);
    }

    public static void main(String[] args) {
        int[] T = {2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18};
        System.out.println(maxAmplitude(T));
    }
}
