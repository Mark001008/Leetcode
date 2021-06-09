package Sort;

//希尔排序
public class Shell {
    public static void sort(Comparable[] a){
        int h = 1;//定义一个增长量
        //根据a.length确定h的值
        while (h < a.length / 2) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            //找到待插元素
            for (int i = h; i < a.length; i++) {
                //
                for (int j = i; j >= h; j -= h) {
                    if (util.less(j-h,j)) {
                        util.exch(a,j-h,j);
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
    }
}
