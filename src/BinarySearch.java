import java.lang.reflect.Array;
import java.util.Arrays;

// 二分查找
// 如果键在数组中，则返回索引，负责返回-1
public class BinarySearch {
    public static int rank(int key,int[] a){
        int lo=0;
        int hi=a.length-1;
        while (lo<=hi){
            int mid=lo+(hi-lo)/2;
            int[] tmp=new int[]{lo,mid,hi};
            System.out.println(Arrays.toString(tmp));
            if(key<a[mid]) {
                hi = mid - 1;
            }
            else if(key>a[mid]) {
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;  // 没有查到
    }

    public static void main(String[] args){
        int[] a={2,4,7,14,56,67};
        System.out.println(rank(4,a));
    }
}