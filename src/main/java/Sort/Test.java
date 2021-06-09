package Sort;

public class Test {
    public static void main(String[] args) {
        Integer[] arr = {1,5,3,7,9,3,20};
        Selection.sort(arr);
        for (Comparable a :
                arr) {
            System.out.print(a + " ");
        }
        System.out.println();
        System.out.println("--------------");
        Insertion.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.println("--------------");
        Shell.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.println("--------------");
        Merge.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
