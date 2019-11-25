package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MergeBU {

    private static Comparable[] aux;//归并所需的辅助数组

    public static void sort(Comparable[] a){//自底向上的归并排序，先合并小数组(size=1)，然后两两合并(size=2)...
        int N=a.length;
        aux=new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz){
            for(int lo=0;lo<N-sz;lo+=sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }

    public static void merge(Comparable[] a,int lo,int mid,int hi){//合并两个有序数组为一个有序数组
        int i=lo,j=mid+1;

        for(int k=lo;k<=hi;k++){
            aux[k]=a[k];
        }

        for(int k=lo;k<=hi;k++){
            if(i>mid){
                a[k]=aux[j++];
            }
            else if(j>hi){
                a[k]=aux[i++];
            }
            else if(less(aux[j],aux[i])){
                a[k]=aux[j++];
            }
            else {
                a[k]=aux[i++];
            }
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
