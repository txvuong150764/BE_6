public class CalculateSqrt {
    public static double calculateSqrt(int n, double e) {
        double low = 0F;
        double high = n;
        double absoluteError = 5e-9;

        while(high - low > 2 * absoluteError) {
            double mid = low + (high - low) / 2;
            if((mid + e) * (mid + e) - n > 0 || (mid - e) * (mid - e) - n > 0) {
                high = mid;
            }
            else if((mid + e) * (mid + e) - n < 0 || (mid - e) * (mid - e) - n < 0){
                low = mid;
            }
            else {
                return mid;
            }
        }

        return low + (high - low) / 2;
    }
    public static void main(String[] args) {
        double a = calculateSqrt(100, 0);
        System.out.println(a + " " + a*a);
    }
}
