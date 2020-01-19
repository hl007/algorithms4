package string;

import java.util.Arrays;

// 低位优先的字符串排序，适用于长度相同的字符串排序
public class LSD {
    public static void sort(String[] a,int w) {  // w为字符串长度
        int N=a.length;
        int R=256;
        String[] aux=new String[N];  // 辅助数组，用于存放排序的字符串
        for(int d=w-1;d>=0;d--) {  // 从字符串的低位开始，依次使用键索引计数法
            // 键索引计数法
            int[] count=new int[R+1];
            for(int i=0;i<N;i++) {  // 计算键出现的频率
                count[a[i].charAt(d)+1]+=1;  // 字符的ascii值加1，便于后面的排序
            }

            for(int i=0;i<R;i++) {  // 将频率转为索引
                count[i+1]+=count[i];   // 此时，count表示某字符的排名（aux的索引）
            }

            for(int i=0;i<N;i++) {
                aux[count[a[i].charAt(d)]++]=a[i];  // 依次将字符串放入aux中，此时根据某键的排序完成
            }

            for(int i=0;i<N;i++) {  // 回写
                a[i]=aux[i];
            }
        }

    }

    public static void main(String[] args) {
        String[] a=new String[]{"asdadasd","fdfdsfsf","sdfdfdff","dfdsfsff"};
        LSD.sort(a,8);
        System.out.println(Arrays.toString(a));

    }
}
