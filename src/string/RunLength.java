package string;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

// 用于压缩位图的游程编码：将位图看作0串（游程）开头，0串与1串交替的序列，输出游程的长度即可
public class RunLength {
    // 压缩
    public static void compress() {
        boolean old=false;
        char count=0;  // 游程长度范围0~255，用八位编码
        while(!BinaryStdIn.isEmpty()) {
            boolean current=BinaryStdIn.readBoolean();  // 读取一位
            if(current!=old) {
                BinaryStdOut.write(count);
                count=0;
                old=current;
            }
            else {
                if(count==255) {
                    BinaryStdOut.write(count);
                    BinaryStdOut.write(0);
                    count=0;
                }
            }
            count++;
        }
        BinaryStdOut.write(count,8);
        BinaryStdOut.close();
    }

    // 解压
    public static void expand() {
        boolean current=false;
        while(!BinaryStdIn.isEmpty()) {
            char c=BinaryStdIn.readChar();
            for(int i=0;i<c;i++) {
                BinaryStdOut.write(current);
            }
            current=!current;
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
