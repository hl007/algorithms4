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

        int code=R+1;

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

    // 解压
    public static void expand() {
        String[] st=new String[L];  // 构建一个逆编译表，key为编码，value为字符串
        int i;
        for(i=0;i<R;i++) {  // 初始化
            st[i]=""+(char)i;
        }
        st[i++]="";  // i=256，结束标记，不会使用

        int codeword = BinaryStdIn.readInt(W);  // 读取第一个编码
        String previous=st[codeword];  // 获取字符串
        while(true) {
            BinaryStdOut.write(previous);
            codeword=BinaryStdIn.readInt(W);  // 再读取一个编码
            if(codeword==R) break;  // 遇到终止标记终止

            String current=st[codeword];  // 获取正在读取的字符串
            if(codeword==i)  current=previous+previous.charAt(0);  // 特殊情况：读取的编码和需要完成的编码相同
            if(i<L) {
                st[i++] = previous + current.charAt(0);  // 插入新的编码
            }
            previous=current;
        }

    }

    public static void main(String[] args) {
        if(args[0].equals("-")) {
            compress();
        }
        else if(args[0].equals("+")) {
            expand();
        }
        else {
            throw new IllegalArgumentException("参数错误");
        }
    }
}
