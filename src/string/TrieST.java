package string;

import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;


// 基于单词查找树的符号表
public class TrieST<Value> {
    private static final int R=256;  // 字母表的基数
    private Node root;  // 根结点
    private int n;  // 符号表中键的数量

    private static class Node {
        private Node[] next=new Node[R];  // 每个结点都有R条链接
        private Object val;
    }

    public Value get(String key) {
        if(key==null) throw new IllegalArgumentException("key不能为空");

        Node x=get(root,key,0);
        if(x==null) {
            return null;
        }
        else {
            return (Value) x.val;
        }

    }

    private Node get(Node x,String key,int d) {
        if(x==null) return null;  // 未命中，返回
        if(d==key.length()) return x;  // 到达字符串尾部，返回
        char c=key.charAt(d);
        return get(x.next[c],key,d+1);  // 递归查找
    }

    public void put(String key,Value val) {
        if(key==null) throw new IllegalArgumentException("key不能为空");
        if(val==null) delete(key);

        root=put(root,key,val,0);
    }

    private Node put(Node x,String key,Value val,int d) {
        if(x==null) x=new Node();  // 对空链接创建一个结点
        if(d==key.length()) {  // 到达字符串的尾部，返回
            if(x.val==null) n++;  // 插入，key数量加1；更新，n不变
            x.val=val;
            return x;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = put(x.next[c], key, val, d + 1);
            return x;
        }
    }

    public void delete(String key) {
        if(key==null) throw new IllegalArgumentException("key不能为空");
        root=delete(root,key,0);
    }


    private Node delete(Node x,String key,int d) {
        if(x==null) return null;
        if(d==key.length()) {  // 命中
            if(x.val!=null) n--;
            x.val=null;
        }
        else {
            char c = key.charAt(d);
            x.next[c]=delete(x.next[c], key, d + 1);
        }

        // 如果子单词树均为空，则移除此节点
        if(x.val != null) return x;
        else {
            for (int i = 0; i < R; i++) {
                if (x.next[i] != null) {
                    return x;
                }
            }
            return null;
        }
    }


    public Iterable<String> keys() {
        return keysWithPrefix("");
    }


    // 包含pre前缀的key
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q=new Queue<>();
        Node x=get(root,pre,0);
        collect(x,pre,q);
        return q;
    }

    private void collect(Node x,String pre,Queue<String> q) {
        if(x==null) return;
        if(x.val != null) q.enqueue(pre);
        for(char c=0;c<R;c++) {
            collect(x.next[c],pre+c,q);
        }
    }


    // 与s匹配的key，"."代表任何字符
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q=new Queue<>();
        collect(root,"",pat,q);
        return q;
    }

    private void collect(Node x,String pre,String pat,Queue<String> q) {
        int d=pre.length();  // 当前位数
        if(x==null) return;
        if(d==pat.length()) {
            if(x.val != null) {
                q.enqueue(pre);
            }
            return;
        }

        char next=pat.charAt(d);
        for(char c=0;c<R;c++) {
            if(next=='.' || next==c) {
                collect(x.next[c],pre+c,pat,q);
            }
        }
    }

    // 该字符串前缀最长的键
    public String longestPrefixOf(String s) {
        int length=search(root,s,0,0);
        return s.substring(0,length);
    }

    private int search(Node x,String s,int d,int length) {  // d为单词的位数，length为键的长度
        if(x==null) return length;  // 遇到空链接，返回
        if(x.val !=null) length=d;
        if(d==s.length()) return length;  // 到达字符串末尾，返回
        char c=s.charAt(d);
        return search(x.next[c],s,d+1,length);
    }

    public static void main(String[] args) {
        TrieST<String> st=new TrieST<>();
        st.put("by","4");
        st.put("sea","6");
        st.put("sells","1");
        st.put("she","0");
        st.put("shells","3");
        st.put("shore","7");
        st.put("the","5");

        System.out.println(st.keysWithPrefix("sh"));
        System.out.println(st.keysThatMatch("s.."));
        System.out.println(st.longestPrefixOf("sheseal"));

    }


}
