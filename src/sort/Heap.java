package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Heap {

    public static void sort(Comparable[] a){//堆排序
        int N=a.length;
        for(int k=N/2;k>=1;k--){//构造最大堆
            sink(a,k,N);
        }
        while(N>1){//具体排序过程，每次删除一个元素，使得剩下的堆仍然有序
            exch(a,1,N--);//将最大元素与堆中最后一个元素交换
            sink(a,1,N);//元素下沉，修复堆
        }
    }

    private static void sink(Comparable[] a,int k,int N){//k为堆的索引，N为堆的大小
        while(2*k<=N){//当某节点变得比子节点小，需要由上自下的堆有序化，下沉
            int j=2*k;//左子节点
            if(j<N && less(a,j,j+1)) j++;//j表示子节点中较大节点索引
            if(!less(a,k,j)) break;
            exch(a,k,j);
            k=j;
        }
    }

    private static boolean less(Comparable[] a,int i,int j){//堆的索引以1开始，1：a[0],2：a[1]，3：a[2]...
        return a[i-1].compareTo(a[j-1])<0;
    }

    private static void exch(Comparable[] a,int i,int j){//堆的索引以1开始，1：a[0],2：a[1]，3：a[2]...
        Comparable t=a[i-1];a[i-1]=a[j-1];a[j-1]=t;
    }

    private static void show(Comparable[] a){//打印数组元素
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){//判断排序后的数组是否有序
        for(int i=1;i<a.length;i++){
            if(less(a,i,i-1)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s=new Scanner(new File("F:\\algorithms4\\algs4-data\\algs4-data\\words3.txt"));
        ArrayList<String> arrayList=new ArrayList<>();
        while (s.hasNext()){
            arrayList.add(s.next());
        }
        Iterator<String> iterator=arrayList.iterator();
        String[] arr=new String[arrayList.size()];
        int index=0;
        while (iterator.hasNext()){
            arr[index++]=iterator.next();
        }

        sort(arr);
        assert isSorted(arr);
        show(arr);
    }

}
