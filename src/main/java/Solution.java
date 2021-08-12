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
        //操作当前l的val
        ListNode l = new ListNode(l1.val + l2.val);
        if (l.val >= 10) {
            l1.next = l1.next != null ? l1.next : new ListNode();
            l1.next.val++;
            l.val = l.val % 10;
        }
        if (l1.next != null || l2.next != null) {
            l1.next = l1.next != null ? l1.next : new ListNode();
            l2.next = l2.next != null ? l2.next : new ListNode();
            //递归，调用
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
        int res = 0;    //最长子串的值
        int head = 0;   //子串的头下标
        int tail = 0;   //子串的尾下标
        HashSet<Character> set = new HashSet<>();
        while (tail < length) {
            if (set.contains(s.charAt(tail))) {     //set是否包含当前为下标
                set.remove(s.charAt(head++));
            } else {
                set.add(s.charAt(tail++));
            }
            res = Math.max(res, set.size());    //res更新取最大值
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
     * 22.括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        gpbacktrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void gpbacktrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            gpbacktrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            gpbacktrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 23. 合并K个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;

        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        head.next = swapPairs(node.next);
        node.next = head;
        return node;
    }

    /**
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) return hair.next;
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    private ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

    /**
     * 26. 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) nums[index++] = nums[i];
        }
        return index;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int hl = haystack.length(), nl = needle.length();
        for (int i = 0; i + nl <= hl; i++) {
            boolean flag = true;
            for (int j = 0; j < nl; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return i;
        }
        return -1;
    }

    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public void nextPermutation(int[] nums)     {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 35. 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target)
                l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }

    /**
     * 53. 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] res = {-1, -1};
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            } else if (sum < target) {
                ++l;
            } else {
                --r;
            }
        }
        return res;
    }

    /**
     * 189. 旋转数组
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 217. 存在重复元素
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 278. 第一个错误的版本
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int f = 1;
        int t = n;
        while (f < t) {
            int mid = f + (t - f) / 2;
            if (isBadVersion(mid)) {
                t = mid;
            } else {
                f = mid + 1;
            }
        }
        return f;
    }

    private boolean isBadVersion(int mid) {
        //same as native method
        return true;
    }

    /**
     * 283. 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

    /**
     * 344. 反转字符串
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 557. 反转字符串中的单词 III
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        String[] strings = s.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (i < strings.length - 1) {
                builder.append(reverseS(strings[i].toCharArray())).append(" ");
            } else {
                builder.append(reverseS(strings[i].toCharArray()));
            }
        }
        return builder.toString();
    }

    private String reverseS(char[] chars) {
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(chars);
    }

    /**
     * 567. 字符串的排列
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));

    }

    /**
     * 671. 二叉树中第二小的节点
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        return 0;
    }

    /**
     * 695. 岛屿的最大面积
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int cl = grid.length, rl = grid[0].length;
        for (int i = 0; i < cl; i++) {
            for (int j = 0; j < rl; j++) {
                max = Math.max(max, mAOIDfs(grid, i,j));
            }
        }
        return max;
    }

    private int mAOIDfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        int sum = 1;
        for (int k = 0; k < 4; k++) {
            int mx = i + dx[k],my = j +dy[k];
            sum += mAOIDfs(grid,mx,my);
        }
        return sum;
    }

    /**
     * 702. 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int finish = nums.length - 1;
        int mid;
        while (start <= finish) {
            mid = start + (finish - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) finish = mid - 1;
            else start = mid + 1;
        }
        return -1;
    }

    /**
     * 二维数组变换位置的辅助数组
     */
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    /**
     * 733. 图像渲染
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor != newColor) {
            dfs(image, sr, sc, currColor, newColor);
        }
        return image;
    }

    private void dfs(int[][] image, int x, int y, int color, int newColor) {

        if (image[x][y] == color) {
            image[x][y] = newColor;
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < image.length && my >= 0 && my < image[0].length) {
                    dfs(image, mx, my, color, newColor);
                }
            }
        }
    }

    /**
     * 876. 链表的中间结点
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        int l = 0;
        ListNode temp = head;
        while (temp != null) {
            l++;
            temp = temp.next;
        }
        int mid = 0;
        while (mid < l / 2) {
            mid++;
            head = head.next;
        }
        return head;
    }

    /**
     * 977. 有序数组的平方
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        for (int i = 0; i <= length - 1; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 二叉树的深度
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
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
     * 两整数之和
     */
    public int getSum(int a, int b) {
        while (b != 0) {  //进位
            int temp = a ^ b;  //异或 不同为一
            b = (a & b) << 1;  //与  全1则1
            a = temp;
        }
        return a;
    }

    /**
     * 寻找右区间
     *
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        int l = intervals.length;
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            int[] temp1 = intervals[i];
            int y = temp1[1];
            for (int j = 0; j < l; j++) {
                int[] temp2 = intervals[j];
                int x = temp2[0];
                if (y <= x) {

                    arr[i] = j;

                    break;
                } else {
                    arr[i] = -1;
                }
            }
        }
        return arr;
    }
}