package Sort;

import static Sort.util.exch;
import static Sort.util.less;

//选择排序
public class Selection {
    public static void sort(Comparable[] a){
        for (int i = 0; i < a.length - 2; i++) {
            //定义一个变量，记录最小元素的索引位置，默认为参与选择排序的第一个元素所在的位置
            int minIdex = i;
            for (int j = i + 1; j < a.length; j++) {
                //需要比较最小索引minIdex处和j索引处的值
                if (less(a[minIdex], a[j])) {
                    minIdex = j;
                }
            }
            exch(a, i, minIdex);
        }
    }
}
