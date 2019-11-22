package search;

import edu.princeton.cs.algs4.Queue;

// 散列表（基于拉链法）
public class SeparateChainingHashST<Key,Value> implements DisorderedSymbolTable<Key,Value> {
    private static final int INI_CAPACITY=997;

    private int n;  // 键值对的数量
    private int m;  // 表的大小
    private SequentialSearchST<Key,Value>[] stList;  // 链表数组

    public SeparateChainingHashST() {
        this(INI_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        this.m=m;
        stList=(SequentialSearchST<Key,Value>[]) new SequentialSearchST[m];
        for (int i=0;i<m;i++) {
            stList[i]=new SequentialSearchST<>();;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        int i=hash(key);
        return stList[i].get(key);
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (val == null) {
            delete(key);
            return;
        }

        if (n>=10*m) resize(2*m);
        int i=hash(key);
        stList[i].put(key,val);
        if (stList[i].contains(key)) n++;
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key,Value> temp=new SeparateChainingHashST<>(chains);
        for (int i=0;i<m;i++) {
            for (Key key:stList[i].keys()) {
                temp.stList[i].put(key,stList[i].get(key));
            }
        }
        this.m=temp.m;
        this.n=temp.n;
        this.stList=temp.stList;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        int i=hash(key);
        stList[i].delete(key);
        if (contains(key)) n--;

        if (m>INI_CAPACITY && n<=2*m) resize(m/2);

    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        int i=hash(key);
        return stList[i].contains(key);
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> q=new Queue<>();
        for (int i=0;i<m;i++) {
            for (Key key:stList[i].keys()) {
                q.enqueue(key);
            }
        }
        return q;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<Integer,String> st=new SeparateChainingHashST<>();
        st.put(2,"a");
        st.put(4,"b");
        st.put(3,"c");
        st.put(2,"d");
        for (Integer s:st.keys()) {
            System.out.println(st.hash(s));
            System.out.println(s+" "+st.get(s));
        }
    }
}
