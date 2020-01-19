package string;


// 字母表
public class Alphabet {
    private char[] alphabet;  // 字母表
    private int[] inverse;  // 字母对应的索引
    private int R;  // 字母表数量

    public Alphabet(String s) {
        boolean[] unicode=new boolean[Character.MAX_VALUE];
        for(int i=0;i<s.length();i++) {
           char c=s.charAt(i);
           if(unicode[c]) {
               throw new IllegalArgumentException("重复的字符："+c);
           }
           unicode[c]=true;
        }

        alphabet=s.toCharArray();
        R=s.length();
        inverse=new int[Character.MAX_VALUE];
        for(int i=0;i<inverse.length;i++) {
            inverse[i]=-1;
        }

        for(int i=0;i<R;i++) {
            inverse[alphabet[i]]=i;
        }
    }

    public char toChar(int index) {
        if(index<0 || index>=R) {
            throw new IllegalArgumentException("索引必须在0~"+R+"之间");
        }
        return alphabet[index];
    }

    // 整数数组转字符串
    public String toChars(int[] indices) {
        StringBuilder s=new StringBuilder(indices.length);
        for(int i=0;i<indices.length;i++) {
            s.append(toChar(i));
        }
        return s.toString();
    }

    public int toIndex(char c) {
        if(c>=inverse.length || inverse[c]==-1) {
            throw new IllegalArgumentException("字符"+c+"不在字母表中");
        }
        return inverse[c];
    }

    // 字符串转为该字母表对应的整数数组
    public int[] toIndices(String s) {
        int[] indices=new int[s.length()];
        char[] source=s.toCharArray();
        for(int i=0;i<s.length();i++) {
            indices[i]=toIndex(source[i]);
        }
        return indices;
    }


    public boolean contains(char c) {
        return inverse[c]!=-1;
    }

    public int R() {
        return R;
    }

    // 字母表所需的比特数
    int lgR() {
        int lgR=0;
        for(int i=R-1;i>=1;i=i/2) {
            lgR++;
        }
        return lgR;
    }

    public static void main(String[] args) {
//        Alphabet a=new Alphabet("abcd");
//        System.out.println(a.toIndices("cd")[0]);
//        System.out.println(a.toIndices("cd")[1]);
        int[] a=new int[]{4,6,7};
        int i=0;
        System.out.print(a[++i]);
    }
}
