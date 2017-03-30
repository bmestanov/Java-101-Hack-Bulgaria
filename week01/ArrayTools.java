package week01;

public class ArrayTools {
    public static <T> String toString(T[] arr) {
        return "[" + glue(arr, ", ") + "]";
    }

    public static <T> String glue(T[] arr, String glue) {
        if(arr.length==0) return "";
        String result = "";
        for(int i=0; i < arr.length-1; i++) {
            result = result + arr[i] + glue;
        }
        return result + arr[arr.length-1];
    }

    public static <T> T[] reverse(T[] arr) {
        int len = arr.length;
        for(int i=0; i < len/2; i++) {
            T temp = arr[i];
            arr[i] = arr[len-i-1];
            arr[len-i-1] = temp;
        }
        return arr;
    }

    public static <T extends Comparable> T[] sort(T[] arr) {
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                if(arr[i].compareTo(arr[j]) < 0){
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static Integer[] range(int from, int to) {
        int step = (from<to?1:-1);
        Integer[] arr = new Integer[Math.abs(from-to)];
        int index = 0;
        for(int i=from; i!=to; i+=step) {
            arr[index] = i;
            index++;
        }
        return arr;
    }
}