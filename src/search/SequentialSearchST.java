package search;

import edu.princeton.cs.algs4.Queue;

// 顺序查找符号表（基于无序链表）
public class SequentialSearchST<Key,Value> implements DisorderedSymbolTable<Key,Value> {
    private int n;  // 键值对的数量
    private Node first;  //  链表

    /**
     *  无序链表，一个结点存储一个键值对和下一个结点的引用
     *
     */
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key,Value val,Node next) {
            this.key=key;
            this.val=val;
            this.next=next;
        }

    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        for (Node x=first;x!=null;x=x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (val ==  null ) {
            delete(key);
            return;
        }

        for (Node x=first;x != null; x=x.next) {
            if (key.equals(x.key)) {  // 如果命中，则更新val
                x.val=val;
                return;
            }
        }

        first=new Node(key,val,first);  // 如果未命中，则新建一个node
        n++;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        first=delete(first,key);
    }

    // 删除链表中的一个节点，返回新的链表（首节点）
    public Node delete(Node x,Key key) {
        if (x == null) return null;

        if (key.equals(x.key)) {  // 如果命中，则返回下一个节点
            n--;
            return x.next;
        }

        x.next=delete(x.next,key);  // 如果未命中, 则从下一个节点继续执行删除操作
        return x;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> queue=new Queue<Key>();
        for (Node x=first;x!=null;x=x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public static void main(String[] args){
        SequentialSearchST<Integer,String> st=new SequentialSearchST<Integer,String>();
        st.put(2,"a");
        st.put(4,"b");
        st.put(-1,"c");
        st.put(6,"d");

        System.out.println(st.keys());
        for (Integer s:st.keys()) {
            System.out.println(s+" "+st.get(s));
        }

    }
}
