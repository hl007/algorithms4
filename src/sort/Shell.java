package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Shell {

    public static void sort(Comparable[] a){//希尔排序
        int N=a.length;
        int h=1;
        while(h<N/3){
            h=3*h+1;//递增序列1/2*(3^k-1)：1，4，13，40，121，364，...
        }
        while(h>=1){//将数组变成h有序，一个h有序数组是由h个互相独立的有序数组一起组成的
            for(int i=h;i<N;i++){
                for(int j=i;j>=h && less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }

    private static boolean less(Comparable v,Comparable w){//对元素进行比较
        return v.compareTo(w)<0;
    }

    private  static void exch(Comparable[] a,int i,int j){//交换元素位置
        Comparable t=a[i]; a[i]=a[j]; a[j]=t;
    }

    private static void show(Comparable[] a){//打印数组元素
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){//判断排序后的数组是否有序
        for(int i=1;i<a.length;i++){
            if(less(a[i],a[i-1])){
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

