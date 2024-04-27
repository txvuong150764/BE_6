import java.util.*;

public class SameDigitSum {
    public static int maxTwoSum(ArrayList<Integer> arr) {
        int max_1 = Integer.MIN_VALUE;
        int max_2 = Integer.MAX_VALUE;

        for(int num : arr) {
            if (max_1 < num) {
                max_2 = max_1;
                max_1 = num;
            }
            else if (max_2 < num) {
                max_2 = num;
            }
        }

        return max_1 + max_2;
    }
    public static int sumOfDigit(int num) {
        int ans = 0;
        while(num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }
    public static int solution(int[] A, int N) {
        int ans = -1;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
        for(int num : A) {
            ArrayList<Integer> itemList = hashMap.get(sumOfDigit(num));
            if (itemList == null) {
                itemList = new ArrayList<Integer>();
                itemList.add(num);
                hashMap.put(sumOfDigit(num), itemList);
            }
            else {
                hashMap.get(sumOfDigit(num)).add(num);
            }
        }

        for(Integer i : hashMap.keySet()) {
            if(hashMap.get(i).size() < 2)
                continue;
            ans = Math.max(ans, maxTwoSum(hashMap.get(i)));
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int N = 4;
        System.out.println(solution(A, N));
    }
}
