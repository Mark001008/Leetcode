import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LCP {
    /**
     * LCP 01. 猜数字
     *
     * @param guess
     * @param answer
     * @return
     */
    public int game(int[] guess, int[] answer) {
        int res = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                res++;
            }
        }
        return res;
    }

    /**
     * LCP 02. 分式化简
     *
     * @param cont
     * @return
     */
    public int[] fraction(int[] cont) {
        int n = cont[cont.length - 1], m = 1;
        for (int i = cont.length - 1; i > 0; i--) {
            int temp = m;
            m = n;
            n = temp;
            n += cont[i - 1] * m;
        }
        return new int[]{n, m};
    }

    /**
     * LCP 03. 机器人大冒险
     * @param command
     * @param obstacles
     * @param x
     * @param y
     * @return
     */
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int a = 0 , b = 0;
        HashMap<Integer , HashSet<Integer>> map = new HashMap<>();
        for(int[] nums : obstacles){
            int i = nums[0] , j = nums[1];
            if(map.containsKey(i)){
                map.get(i).add(j);
            }else{
                HashSet<Integer> set = new HashSet<>();
                set.add(j);
                map.put(i , set);
            }
        }
        int n = command.length();
        for(int i = 0 ; i <= n ; i++){
            //一次路径走完时，重新走，总会超过终点，因此不会死循环
            if(i == n) i = 0;
            char c = command.charAt(i);
            if(c == 'U'){
                b++;
            }else{
                a++;
            }
            if(a == x && b == y) return true;
            if(a > x || b > y) return false;
            if(map.containsKey(a)){
                if(map.get(a).contains(b)) return false;
            }
        }
        return false;
    }

    /**
     * LCP 04. 覆盖
     * @param n
     * @param m
     * @param broken
     * @return
     */
    public int domino(int n, int m, int[][] broken) {
        return 0;
    }
    /*
    "RRU"
    [[5, 5], [9, 4], [9, 7], [6, 4], [7, 0], [9, 5], [10, 7], [1, 1], [7, 5]]
    1486
    743
     */
    @Test
    public void test() {
        int[][] ints = {{5,5},{9,4},{9,7},{6,4},{7,0},{9,5},{10,7},{1,1},{7,5}};
        boolean urr = robot("URR", ints, 1486, 743);
        System.out.println(urr);
    }

    /**
     * LCP 28. 采购方案
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        int res = 0;
        int left = 0;
        int right = nums.length - 1;
        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum <= target) {
                res += right - left;
                left++;
            } else {
                right--;
            }
            res %= 1000000007;
        }
        return res % 1000000007;
    }


}
