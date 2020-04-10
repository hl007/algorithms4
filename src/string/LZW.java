package string;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.TST;

// LZW压缩：将8位的字节流转为12位编码
public class LZW {
    private static final int R=256;  // 2^8，输入的字符数
    private static final int L=4096;  // 2^12，编码总数
    private static final int W=12;  // 编码宽度

    // 压缩
    public static void compress() {
        String s=BinaryStdIn.readString();
        TST<Integer> st=new TST<>();  // 三向单词查找树（符号表），用于查询一个字符串的最长前缀
        for(int i=0;i<R;i++) { // 初始化，将每个字符插入符号表
            st.put(""+(char)i,i);
        }

        int code=R+1;  // 终止标记

        while(s.length()>0) {
            String prefix=st.longestPrefixOf(s);  // 获取该字符串在符号表中的最长前缀
            BinaryStdOut.write(st.get(prefix),W);

            if(prefix.length()<s.length() && code<L) {
                st.put(s.substring(0,prefix.length()+1),code);  // 将最长前缀及其后面的一个字符作为键加入符号表
                code++;
            }
            s=s.substring(prefix.length());
        }
        BinaryStdOut.write(R,W);  // 写入终止标记
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if(args[0].equals("-")) {
            compress();
        }
    }
}
