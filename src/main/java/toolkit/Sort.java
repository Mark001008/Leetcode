package toolkit;

import org.junit.Test;

/**
 * 八大排序算法
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
    public static void selectionSort(int[] arr) {
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

    /**
     * 冒泡排序
     * 时间复杂度O(N^2)
     *
     * @param arr 需要排序的数组
     */
    public static void bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 时间复杂度O(N^2)
     *
     * @param arr 需要排序的数组
     */
    public static void insertionSort(int[] arr) {
        //将a[]按升序排列
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            //将a[i]插入到a[i-1]，a[i-2]，a[i-3]……之中
            for (int j = i; j > 0 && (arr[j] > arr[j - 1]); j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param source
     */
    public static void shellSort(int[] source) {
        int group = source.length / 2;
        while (group > 0) {
            for (int i = group; i < source.length; i++) {
                if (source[i - group] > source[i]) {
                    int insertData = source[i];
                    int j = i;
                    while (j > group - 1 && source[j - group] > insertData) {
                        source[j] = source[j - group];
                        j -= group;
                    }
                    source[j] = insertData;
                }
            }
            group = group / 2;
        }
    }

    /**
     * 归并排序
     *
     * @param a
     * @param start
     * @param end
     */
    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(arr, start, mid);//对左侧子序列进行递归排序
            mergeSort(arr, mid + 1, end);//对右侧子序列进行递归排序
            merge(arr, start, mid, end);//合并
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[arr.length];//辅助数组
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针

        while (p1 <= mid && p2 <= right) {
            if (arr[p1] <= arr[p2])
                tmp[k++] = arr[p1++];
            else
                tmp[k++] = arr[p2++];
        }
        while (p1 <= mid) tmp[k++] = arr[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p2 <= right) tmp[k++] = arr[p2++];//同上
        //复制回原素组
        for (int i = left; i <= right; i++)
            arr[i] = tmp[i];
    }

    /**
     * 快速排序
     * 时间复杂度O(NlogN)
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSort(int arr[], int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) arr = quickSort(arr, start, i - 1);
        if (j + 1 < end) arr = quickSort(arr, j + 1, end);
        return (arr);
    }

    /**
     * 桶排序
     * @param data
     */
    public static void basketSort(int data[]){  //data为待排序数组
        int n = data.length;
        int bask[][] = new int[10][n];
        int index[] = new int[10];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int length = Integer.toString(data[i]).length();
            max = max > length ? max
                    : length;
        }
        String str;
        for (int i = max - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                str = "";
                if (Integer.toString(data[j]).length() < max) {
                    for (int k = 0; k < max - Integer.toString(data[j]).length(); k++)
                        str += "0";
                }
                str += Integer.toString(data[j]);
                bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = data[j];
            }
            int pos = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < index[j]; k++) {
                    data[pos++] = bask[j][k];
                }
            }
            for (int x = 0; x < 10; x++) index[x] = 0;
        }
    }

    /**
     * 选择排序-堆排序
     * @param array 待排序数组
     * @return 已排序数组
     */
    public static int[] heapSort(int[] array) {
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        return array;
    }

    /**
     * 整个堆排序最关键的地方
     * @param array 待组堆
     * @param i 起始结点
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i  =  k;
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }
    @Test
    public void test() {
        int[] arr = {5, 2, 4, 1, 7, 6, 3, 8, 9};
        basketSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
