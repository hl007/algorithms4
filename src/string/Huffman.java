package string;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import sort.MinPQ;

import java.util.Arrays;

// 霍夫曼压缩
public class Huffman {
    private static final int R=256;  // 扩展ascii编码

    // 压缩
    public static void compress() {
        String s=BinaryStdIn.readString();  // 读取输入
        char[] chars=s.toCharArray();
        // 计算每个字符的频率
        int[] freq=new int[R];
        for(char c:chars) {
            freq[c]++;
        }

        Node x=buildTrie(freq);  // 构建单词查找树
        String[] st=new String[R];
        buildCode(st,x,"");  // 构建编译表

        writeTrie(x);  // 将单词查找树转为比特流输出
        BinaryStdOut.write(chars.length);  // 输出字符数量
        for(char c:chars) {  // 将压缩后的字符输出
            for(char p:st[c].toCharArray()) {
                if(p=='0') BinaryStdOut.write(false);
                else       BinaryStdOut.write(true);
            }
        }

        BinaryStdOut.close();
    }

    // 解压
    public static void expand() {
        Node root=readTrie();  // 读取单词查找树
        int length=BinaryStdIn.readInt();  // 获取未压缩字符流的大小

        for(int i=0;i<length;i++) {
            Node x=root;
            while(!x.isLeaf()) { // 从根结点遍历到叶结点
                if(BinaryStdIn.readBoolean())  {  // 如果是1，则移动到右子节点
                    x=x.right;
                }
                else {
                    x= x.left;
                }
            }
            BinaryStdOut.write(x.ch,8);  // 获取字符
        }
        BinaryStdOut.close();
    }

    // 将单词查找树转为比特流输出
    private static void writeTrie(Node x) {
        if(x.isLeaf()) {  // 如果是叶结点
            BinaryStdOut.write(true);  // 写入比特1
            BinaryStdOut.write(x.ch);;  // 写入字符
        }
        else {
            BinaryStdOut.write(false);  // 写入比特0
            writeTrie(x.left);  // 继续递归
            writeTrie(x.right);
        }
    }

    // 从比特流中重建单词查找树
    private static Node readTrie() {
        if(BinaryStdIn.readBoolean()) {  // 如果比特是1
            char c=BinaryStdIn.readChar();
            return new Node(c,0,null,null);
        }
        else {
            return new Node('\0',0,readTrie(),readTrie());  // 递归读取左结点和右结点
        }
    }

    // 根据单词查找树构建编译表（通过二叉树的前序遍历实现）
    private static void buildCode(String[] st,Node x,String s) {
        if(x.isLeaf()) st[x.ch]=s;
        else {
            buildCode(st, x.left, s + "0");  // 左子结点为0
            buildCode(st, x.right, s + "1");  // 右子结点为1
        }
    }

    // 构建一棵Huffman单词查找树：先为每个字符构建一棵二叉树，在将这些树中频率较小的两棵树合并，直至剩下一棵树
    // 构造结束后，频率较小的字符离根结点远，频率较大的字符离根结点近，使得压缩后所占空间最小
    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq=new MinPQ<>(freq.length);  // 最小优先队列

        // 为每个字符构建一棵二叉树
        for(char i=0;i<freq.length;i++) {
            if(freq[i]>0) {
                Node x = new Node(i, freq[i], null, null);
                pq.insert(x);
            }
        }

        // 只有一种字符
        if(pq.size()==1) {
            pq.insert(new Node('\0',0,null,null));
        }

        while(pq.size()>1) {  // 合并频率较小的两棵树
            Node x=pq.delMin();
            Node y=pq.delMin();
            Node merge=new Node('\0',x.freq+y.freq,x,y);  // '\0'为空字符
            pq.insert(merge);
        }

        return pq.delMin();
    }

    // 单词查找树
    private static class Node implements Comparable<Node> {
        private final char ch;  // 编码的字符
        private final int freq;  // 该树中字符数量
        private final Node left,right;  // 左子结点，右子结点

        Node(char ch,int freq,Node left,Node right) {
            this.ch=ch;
            this.freq=freq;
            this.left=left;
            this.right=right;
        }

        // 判断是否为叶子结点
        private boolean isLeaf() {
            return left==null && right==null;
        }

        @Override
        public int compareTo(Node x) {
            return freq-x.freq;
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
