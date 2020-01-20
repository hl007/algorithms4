package string;

// 三向单词查找树, 一个结点一个字母，一个值，三条链接分别小于、等于、大于该结点的字母。
public class TST<Value> {
    private Node root;
    private int n;


    private static class Node {
        char c;
        Node left,mid,right;
        Object val;
    }

    public Value get(String key) {
        if(key==null) throw new IllegalArgumentException("key不能为空");
        if(key.length()==0) throw new IllegalArgumentException("key的长度必须大于0");

        Node x=get(root,key,0);
        if(x==null) return null;
        else        return (Value) x.val;
    }

    private Node get(Node x,String key,int d) {
        if(x==null) return null;
        char c=key.charAt(d);
        if(c<x.c) return get(x.left,key,d);
        if(c>x.c) return get(x.right,key,d);
        else {
            if(d==key.length()-1) {
                return x;
            }
            else {
                return get(x.mid, key, d + 1);
            }
        }
    }

    public void put(String key,Value val) {
        if(key==null) throw new IllegalArgumentException("key不能为空");
        if(val==null) delete(key);;

        root=put(root,key,val,0);
    }

    private Node put(Node x,String key,Value val,int d) {
        char c=key.charAt(d);
        if(x==null) {
            x=new Node();
            x.c=c;
        }

        if(c<x.c) x.left=put(x.left,key,val,d);
        if(c>x.c) x.right=put(x.right,key,val,d);
        else {
            if(d==key.length()-1) {  // 命中
                if(x.val==null) n++;
                x.val=val;
            }
            else {
                x.mid=put(x.mid, key, val,d + 1);
            }
        }
        return x;
    }

    public int size() {
        return n;
    }

    public void delete(String key) {

    }

    public static void main(String[] args) {
        TST<String> tst=new TST<>();
        tst.put("by","4");
        tst.put("sea","6");
        tst.put("sells","1");
        tst.put("she","0");
        tst.put("shells","3");
        tst.put("shore","7");
        tst.put("the","5");

        System.out.println(tst.size());
        System.out.println(tst.get("by"));
        System.out.println(tst.get("shells"));
    }

}
