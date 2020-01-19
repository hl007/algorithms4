package string;

import java.util.Arrays;
import java.util.Random;

// 高位优先的字符串排序，先对所有字符串的高位作为键，进行键索引计数排序，再对子数组递归的使用键索引计数法
// 适用于不同长度的字符串排序
public class MSD {
    private static final int R=256;  // 基数
    private static final int M=15;  // 阈值，对于长度小于M的子数组进行插入排序

    public static void sort(String[] a) {
        int N=a.length;
        String[] aux=new String[N];
        sort(a,0,N-1,0,aux);
    }

    private static int charAt(String s,int d) {
        assert(d>=0 && d<=s.length());
        if(d==s.length()){
            return -1;
        }
        else{
            return s.charAt(d);
        }
    }

    // 对a[lo,hi]进行排序
    private static void sort(String[] a,int lo,int hi,int d,String[] aux) {  // d为字符串的位置，
        System.out.println("lo: "+lo);
        System.out.println("hi: "+hi);
        System.out.println("d: "+d);

        if(hi-lo<M) {  // 对于长度小于M的子数组进行插入排序；递归到字符串结尾会在此返回
            insertion_sort(a,lo,hi,d);
            return;
        }

        // 键索引计数法
        int[] count=new int[R+2];
        for(int i=lo;i<=hi;i++) {  // 计算键出现的频率
            int c=charAt(a[i],d);
            count[c+2]+=1;  // 字符的ascii值加1，便于后面的排序
        }

        for(int i=0;i<R+1;i++) {  // 将频率转为索引
            count[i+1]+=count[i];   // 此时，count表示某字符的排名（aux的索引）
        }

        for(int i=lo;i<=hi;i++) {
            int c=charAt(a[i],d);
            aux[count[c+1]++]=a[i];  // 依次将字符串放入aux中，此时根据某键的排序完成
        }

        for(int i=lo;i<=hi;i++) {  // 回写
            a[i]=aux[i-lo];  // i-lo
        }

        // 递归子数组
        for(int i=0;i<R;i++) {
            System.out.println("递归");
            sort(a,lo+count[i],lo+count[i+1]-1,d+1,aux);
        }
    }

    // 字符串数组的插入排序
    private static void insertion_sort(String[] a,int lo,int hi,int d){
        for(int i=lo+1;i<=hi;i++){
            for(int j=i;j>lo && less(a[j],a[j-1],d);j--) {
                exch(a,j,j-1);
            }
        }
    }

    private static boolean less(String v,String w,int d) { // 对两个字符串进行比较
        for(int i=d;i<Math.min(v.length(),w.length());i++) {
            if(v.charAt(i)>w.charAt(i)) return false;
            if(v.charAt(i)<w.charAt(i)) return true;
        }
        return v.length()<w.length();  // v="abd" w="abdef"，一个字符串是另一个字符串的一部分
    }

    private  static void exch(String[] a,int i,int j){//交换元素位置
        String t=a[i]; a[i]=a[j]; a[j]=t;
    }

    public static String generateStr(int len){
        final String allChar = "ABCDEFG";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <len ; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return  sb.toString();
    }



    public static void main(String[] args) {
        String[] a=new String[200];
        for(int i=0;i<50;i++) {
            a[i]=generateStr(8);
        }
        for(int i=50;i<100;i++) {
            a[i]=generateStr(7);
        }

        for(int i=100;i<150;i++) {
            a[i]=generateStr(5);
        }

        for(int i=150;i<200;i++) {
            a[i]=generateStr(3);
        }

        String[] b=new String[]{"a","a","a","a","a","a"};
        MSD.sort(b);
        System.out.println(Arrays.toString(b));
    }
}
