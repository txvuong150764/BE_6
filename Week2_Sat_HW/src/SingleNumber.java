public class SingleNumber {
    public static int singleNumber(int[] nums) {
        int[] tmp = new int[nums.length + 1];

        for (int num : nums) {
            tmp[num]++;
        }

        for (int i = 0; i < tmp.length; i++) {
            if(tmp[i] == 1)
                return i;
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(singleNumber(nums));
    }
}
