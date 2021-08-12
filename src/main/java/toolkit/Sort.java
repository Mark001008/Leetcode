package toolkit;

import org.junit.Test;

/**
 * 排序算法
 */
public class Sort {
    /**
     * 交换位置的方法
     * @param arr 需要交换位置的数组
     * @param i   位置 i
     * @param j   位置 j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 选择排序
     * 时间复杂度O(N^2)
     * @param arr 需要排序的数组
     */
    public static void selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 交换次数
            // 先假设每次循环时，最小数的索引为i
            int minIndex = i;// 每一个元素都和剩下的未排序的元素比较
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {//寻找最小数
                    minIndex = j;//将最小数的索引保存
                }
            }//经过一轮循环，就可以找出第一个最小值的索引，然后把最小值放到i的位置
            swap(arr, i, minIndex);
        }
    }

    @Test
    public void selectionTest() {
        int[] arr = {5, 4, 7, 10, 2, 8};
        selection(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 冒泡排序
     * 时间复杂度O(N^2)
     * @param arr 需要排序的数组
     */
    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    @Test
    public void bubbleTest() {
        int[] arr = {5, 4, 7, 10, 2, 8};
        bubble(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    /**
     * 插入排序
     * 时间复杂度O(N^2)
     * @param arr 需要排序的数组
     */
    public static void insertion(int[] arr) {
        //将a[]按升序排列
        for (int i = 1; i < arr.length; i++) {
            //将a[i]插入到a[i-1]，a[i-2]，a[i-3]……之中
            for (int j = i; j > 0 && (arr[j] < (arr[j - 1])); j--) {
                swap(arr,j,j-1);
            }
        }
    }

    @Test
    public void insertionTest() {
        int[] arr = {5, 4, 7, 10, 2, 8};
        insertion(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
