package Sort;

/*
归并排序
时间复杂度O(1)
 */

/*
归并操作的工作原理如下：
第一步：申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
第二步：设定两个指针，最初位置分别为两个已经排序序列的起始位置
第三步：比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
重复步骤3直到某一指针超出序列尾
将另一序列剩下的所有元素直接复制到合并序列尾
 */
public class Merge {

    //归并所需要的辅助数组
    private static Comparable[] assist;

    //比较v元素是否小于w元素
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /*没有用到该方法
      //数组中元素i和元素j交换位置
      private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
      }
    */
    //对数组a中的元素进行排序
    public static void sort(Comparable[] a) {
        //1.初始化辅助数组assist
        assist = new Comparable[a.length];
        //2.定义一个low变量和high变量，分别用来记录数组中的最小索引和最大索引
        int low = 0;
        int high = a.length - 1;
        //3.调用sort重载方法完成对数值a从元素low到元素high的排序
        sort(a, low, high);
    }

    //对数组a中冲low到high进行排序
    private static void sort(Comparable[] a, int low, int high) {
        //做安全性校验
        if (high <= low) {
            return;
        }
        //从low到high分为两个组
        int mid = low + (high - low) / 2;
        //分别对每一组元素进行排序
        sort(a, low, mid);
        sort(a, mid + 1, high);
        //再把两个元素中的数据进行归并
        merge(a, low, mid, high);
    }

    //对数组中，从low到mid分为一组，从mid+1到high分为一组，对这两组元素进行归并
    private static void merge(Comparable[] a, int low, int mid, int high) {
        //定义三个指针
        int i = low;
        int p1 = low;
        int p2 = mid + 1;
        //遍历，移动p1指针和p2指针，比较索引出的值，找出小的放在辅助数组的对应索引处
        while (p1 <= mid && p2 <= high) {
            //比较对应索引处的值
            if (less(a[p1], a[p2])) {
                assist[i++] = a[p1++];
            } else {
                assist[i++] = a[p2++];
            }
        }
        //遍历，如果p1指针没有走完，那么顺序移动p1指针，把对应的运输放到辅助数组的对应索引处
        while (p1 <= mid) {
            assist[i++] = a[p1++];
        }
        //遍历，如果p2指针没有走完，那么顺序移动p2指针，把对应的运输放到辅助数组的对应索引处
        while (p2 <= high) {
            assist[i++] = a[p2++];
        }
        //把辅助数组中的元素拷贝到原数组中
        for (int index = low; index < high; index++) {
            a[index] = assist[index];
        }
    }
}
