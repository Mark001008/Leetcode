import org.junit.Test;

import java.util.*;

public class Solution {
    /**
     * 1. 两数之和
     * 核心思想：冒泡排序
     */
    public int[] twoSum(int[] nums, int target) {
        int[] ints = new int[2];
        int nl = nums.length;
        for (int i = 0; i < nl; i++) {
            for (int j = i + 1; j < nl; j++) {
                if (nums[i] + nums[j] == target) {
                    ints[0] = i;
                    ints[1] = j;
                }
            }
        }
        return ints;
    }

    /**
     * 2. 两数相加
     * 递归 找到循环体和结束条件 方法本身调用本身
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(l1.val + l2.val);
        if (l.val >= 10) {
            l1.next = l1.next != null ? l1.next : new ListNode();
            l1.next.val++;
            l.val = l.val % 10;
        }
        if (l1.next != null || l2.next != null) {
            l1.next = l1.next != null ? l1.next : new ListNode();
            l2.next = l2.next != null ? l2.next : new ListNode();

            l.next = addTwoNumbers(l1.next, l2.next);
        }
        return l;
    }

    /**
     * 3. 无重复字符的最长子串（set集合）
     * 双指针 满足要求移动头尾指针
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (0 == length) return 0;
        int res = 0;
        int head = 0;
        int tail = 0;
        HashSet<Character> set = new HashSet<>();
        while (tail < length) {
            if (set.contains(s.charAt(tail))) {
                set.remove(s.charAt(head++));
            } else {
                set.add(s.charAt(tail++));
            }
            res = Math.max(res, set.size());
        }
        return res;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 暴力解法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] arr = new int[l1 + l2];
        System.arraycopy(nums1, 0, arr, 0, l1);
        System.arraycopy(nums2, 0, arr, l1, l2);
        Arrays.sort(arr);
        if (arr.length % 2 == 0) {
            return (double) (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2;
        } else {
            return arr[arr.length / 2];
        }
    }

    /**
     * 5. 最长回文子串
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) return s;
        char[] chars = s.toCharArray();
        int maxL = 1;
        int index = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (j - i + 1 > maxL && IsLongestP(chars, i, j)) {
                    maxL = j - i + 1;
                    index = i;
                }
            }
        }
        return s.substring(index, index + maxL);
    }

    private boolean IsLongestP(char[] c, int b, int e) {
        while (b < e) {
            if (c[b] != c[e]) {
                return false;
            }
            b++;
            e--;
        }
        return true;
    }

    /**
     * 6. Z字形变换
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean flag = false;
        for (char c :
                s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) flag = !flag;
            curRow += flag ? 1 : -1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row :
                rows) {
            ret.append(row);
        }

        return ret.toString();
    }

    /**
     * 7. 整数反转
     */
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        int neg = x > 0 ? 1 : -1;
        x *= neg;
        int ret = 0;
        while (x > 0) {
            int n = ret;
            n *= 10;
            n += x % 10;
            x /= 10;
            if (n / 10 != ret) return 0;
            ret = n;
        }
        return ret * neg;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     */
    public int myAtoi(String s) {
        char[] array = s.toCharArray();
        int length = array.length;
        int index = 0;
        int res = 0;
        boolean flag = true;
        while (index <= length && array[index] == ' ') index++;
        if (array[index] == '-') {
            index++;
            flag = false;
        }
        if (array[index] == '+') {
            index++;
        }
        if (!Character.isDigit(array[index])) {
            return 0;
        }
        while (index < length && Character.isDigit(array[index])) {
            int dight = array[index] - '0';
            if (res > (Integer.MAX_VALUE - dight) / 10) {
                return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + dight;
            index++;
        }
        return flag ? res : -res;
    }

    /**
     * 9. 回文数
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0) return false;
        int rx = 0;
        while (x > rx) {
            rx = rx * 10 + x % 10;
            x /= 10;
        }
        return x == rx || x == rx / 10;
    }

    /**
     * 10. 正则表达式匹配
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 11. 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;
        int max = 0;
        while (head <= tail) {
            int h = Math.min(height[head], height[tail]);
            max = Math.max(max, (tail - head) * h);
            if (height[head] > height[tail]) {
                tail--;
            } else {
                head++;
            }
        }
        return max;
    }

    /**
     * 12. 整数转罗马数字
     */
    public String intToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    /**
     * 13. 罗马数字转整数
     */
    public int romanToInt(String s) {
        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;

    }

    /**
     * 14. 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 15. 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        int len = nums.length;
        if (len < 3) return res;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return res;
    }

    /**
     * 16. 最接近的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length;
        if (length < 3) return 0;
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < length; i++) {
            int l = i + 1;
            int r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                } else {
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 17. 电话号码的字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * 18. 四数之和
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = getLength(head);
        ListNode node = new ListNode(0, head);
        ListNode cur = node;
        for (int i = 0; i < length - n; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode res = node.next;
        return res;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    /**
     * 20. 有效的括号
     */
    public boolean isValid(String s) {
        int length = s.length();
        if (length % 2 == 1) return false;
        HashMap<Character, Character> map = new HashMap<Character, Character>() {
            {
                put('(', ')');
                put('{', '}');
                put('[', ']');
            }
        };
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            //如果索引在map中
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 21. 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        int mid = nums.length / 2;
        while (mid != l || mid != r) {
            if (target > nums[mid]) {
                l = mid;
                mid = (mid + r) / 2;
            } else if (target < nums[mid]) {
                mid = r - 1;
                mid = (l + mid);
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 二叉树的深度
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 统计位数为偶数的数字
     */
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isEven(nums[i])) {
                res++;
            }
        }
        return res;
    }

    private boolean isEven(int i) {
        boolean flag = false;
        while (i / 10 >= 1) {
            flag = !flag;
            i = i / 10;
        }
        return flag;
    }

    /**
     * 直线上最多的点数
     */
    public int maxPoints(int[][] points) {
        int res = 1;
        //遍历二维数组
        for (int i = 0; i < points.length; ++i) {
            //把二维数组中单独一个元素放入数组m中
            int[] m = points[i];
            for (int j = i + 1; j < points.length; ++j) {
                //m[0]表示横坐标  m[1]表示纵坐标
                int dx = points[j][0] - m[0]; //x
                int dy = points[j][1] - m[1]; //y
                int count = 0;
                int d = dx * m[1] - dy * m[0];
                for (int[] p : points) {
                    if (dx * p[1] == dy * p[0] + d)
                        ++count;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    /**
     *两整数之和
     */
    public int getSum(int a, int b) {
        while(b != 0){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }
    @Test
    public void test() {
        System.out.println(getSum(1, 2));
    }
}

