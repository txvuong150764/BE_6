public class FindNumber {
    public static int findNumber(int num, int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while(high >= low) {
            int mid = low + (high - low) / 2;
            if(arr[mid] > num) {
                high = mid - 1;
            }
            else if(arr[mid] < num) {
                low = mid + 1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] arr = { 4, 5, 11, 44, 56, 92, 100 };
        System.out.println(findNumber(11, arr));
    }
}
