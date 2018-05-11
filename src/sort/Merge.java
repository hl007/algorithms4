package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Merge {

    private static Comparable[] aux;//归并所需的辅助数组

    public static void sort(Comparable[] a){
        aux=new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi){//自顶向下的归并排序
        if(hi<=lo) return;
        int mid=lo+(hi-lo)/2;
        sort(a,lo,mid);//将左半边排序
        sort(a,mid+1,hi);//将右半边排序
        if(less(a[mid+1],a[mid])) {//如果数组已经有序，就无需合并
            merge(a, lo, mid, hi);
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
