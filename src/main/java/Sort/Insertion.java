package Sort;

/*
插入排序
时间复杂度O(n^2)
 */

/*
插入排序是指在待排序的元素中，假设前面n-1(其中n>=2)个数已经是排好顺序的，现将第n个数插到前面已经排好的序列中，
然后找到合适自己的位置，使得插入第n个数的这个序列也是排好顺序的。
按照此法对所有元素进行插入，直到整个序列排为有序的过程，称为插入排序 [3]  。
 */

import static Sort.util.exch;
import static Sort.util.less;

public class Insertion {
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && less(j,j+1); j--) {
                exch(a,j,j+1);
            }
        }
    }
}
