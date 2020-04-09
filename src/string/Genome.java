package string;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

// 用于压缩基因组的双位编码压缩
public class Genome {
    // 压缩
    public static void compress() {
        Alphabet alphabet=new Alphabet("ATCG");  // 字母表
        String s=BinaryStdIn.readString();
        int length=s.length();
        BinaryStdOut.write(length);
        for(char c:s.toCharArray()) {
            BinaryStdOut.write(alphabet.toIndex(c),2);  // 用两位表示一个字符
        }
        BinaryStdOut.close();
    }

    // 解压
    public static void expand() {
        Alphabet alphabet=new Alphabet("ATCG");  // 字母表
        int length=BinaryStdIn.readInt();
        for(int i=0;i<length;i++) {
            int index=BinaryStdIn.readChar(2);
            BinaryStdOut.write(alphabet.toChar(index));
        }
        BinaryStdOut.close();
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
