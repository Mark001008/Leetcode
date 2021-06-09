package Sort;
/*
快速排序
快速排序的一次划分算法从两头交替搜索，直到low和hight重合，因此其时间复杂度是O(n)；而整个快速排序算法的时间复杂度与划分的趟数有关。
1，理想的情况是，每次划分所选择的中间数恰好将当前序列几乎等分，经过log2n趟划分，便可得到长度为1的子表。这样，整个算法的时间复杂度为O(nlogn)。
2，最坏的情况是，每次所选的中间数是当前序列中的最大或最小元素，这使得每次划分所得的子表中一个为空表，另一子表的长度为原表的长度-1。
这样，长度为n的数据表的快速排序需要经过n趟划分，使得整个排序算法的时间复杂度为O(n2)。
 */

/*
快速排序算法通过多次比较和交换来实现排序，其排序流程如下：
(1)首先设定一个分界值，通过该分界值将数组分成左右两部分。
(2)将大于或等于分界值的数据集中到数组右边，小于分界值的数据集中到数组的左边。
此时，左边部分中各元素都小于或等于分界值，而右边部分中各元素都大于或等于分界值。
(3)然后，左边和右边的数据可以独立排序。对于左侧的数组数据，
又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。右侧的数组数据也可以做类似处理。
(4)重复上述过程，可以看出，这是一个递归定义。通过递归将左侧部分排好序后，再递归排好右侧部分的顺序。
当左、右两个部分各数据排序完成后，整个数组的排序也就完成了。
 */
public class Quick {
    //比较v元素是否小于w元素
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //数组中元素i和元素j交换位置
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //对组内的元素进行排列
    public static void sort(Comparable[] a) {
        int low = 0;
        int high = a.length - 1;
        sort(a, low, high);
    }

    //对数组a中从索引low到high之间的元素进行排序
    public static void sort(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }

        //需要对数组中low索引到high索引处的元素进行分组（左子组和又右子组）
        int partition = partition(a, low, high);//返回的是分组的临界值所在的索引，分解位置交换的索引
        //使左子组有序
        sort(a, low, partition - 1);
        //使右子组有序
        sort(a, partition + 1, high);
    }

    //对数组a中从索引low到索引high之间的元素进行分组，并分组界限对应的索引
    public static int partition(Comparable[] a, int low, int high) {
        //确定分界值(key)
        Comparable key = low;

        //定义两个指针，分别指向待切元素的最小索引和最大索引的下一个位置
        int left = low;
        int right = high + 1;
        //切分
        while (true) {
            //先从右往左扫描，移动right指针，找到一个比分界值小的元素，停止
            while (less(key, a[--right])) {
                if (right == low) {
                    break;
                }
            }
            //再从左往右扫描，移动left指针，找到一个比分界值大的元素，停止
            if (left >= right) {
                break;
            } else {
                exchange(a, left, right);
            }
        }
        //交换分界值
        exchange(a, low, right);
        return right;
    }
}
