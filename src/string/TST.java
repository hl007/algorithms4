package string;

// 三向单词查找树, 一个结点一个字母，一个值，三条链接分别小于、等于、大于该结点的字母。
public class TST<Value> {
    private Node root;


    private static class Node {
        char c;
        Node left,mid,right;
        Object val;
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
        if(x==null) return null;
        if(d==key.length()) return x;
        char c=key.charAt(d);
        if(c<x.c) return get(x.left,key,d+1);
        if(c>x.c) return get(x.right,key,d+1);
        else      return get(x.mid,key,d+1);
    }

    public void put(String key,Value val) {

    }

//    private Node put(Node  )


}
