package search;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

// 二分查找符号表（基于有序数组）
public class BinarySearchST<Key extends Comparable<Key>,Value>  implements OrderedSymbolTable<Key,Value> {
    private static final int INIT_CAPACITY=2;
    private Key[] keys;
    private Value[] vals;
    private int n=0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys=(Key[]) new Comparable[capacity];
        vals=(Value[]) new Object[capacity];
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (isEmpty()) return null;

        int k=rank(key);
        if (k<n && key.compareTo(keys[k])==0) return vals[k];  // 命中
        else return null;  // 未命中
    }


    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (val == null) {
            delete(key);
            return;
        }

        int k=rank(key);

        if (k<n && key.compareTo(keys[k])==0) {  // 命中，则更新val；k<n用于首次插入键值对的情况
            vals[k]=val;
        }
        else {  // 未命中，则插入键值对
            // 考虑到数组容量
            if (n==keys.length) resize(2*n);

            for (int i=n;i>k;i--) {
                keys[i]=keys[i-1];
                vals[i]=vals[i-1];
            }
            keys[k]=key;
            vals[k]=val;
            n++;
        }
    }

    // 数组扩容
    private void resize(int capacity) {
        assert capacity>n;
        Key[] tempk=(Key[]) new Comparable[capacity];
        Value[] tempv=(Value[]) new Object[capacity];
        for (int i=0;i<n;i++) {
            tempk[i]=keys[i];
            tempv[i]=vals[i];
        }
        keys=tempk;
        vals=tempv;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        int k=rank(key);
        if (k==n || key.compareTo(keys[k])!=0) {  // 未命中
            return;
        }
        else {  // 命中，则删除键值对
            for (int i=k;i<n-1;i++) {
                keys[i]=keys[i+1];
                vals[i]=vals[i+1];
            }
            // 避免元素游离
            keys[n-1]=null;
            vals[n-1]=null;

            n--;
        }

        if (n>0 && n==keys.length/4) resize(keys.length/2); // 如果数组容量过大，则调整大小

    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("key不能为空");
        if (hi == null) throw new IllegalArgumentException("key不能为空");

        if (lo.compareTo(hi) > 0) return 0;

        if(contains(hi)) return rank(hi)-rank(lo)+1;
        else return rank(hi)-rank(lo);
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(),max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("key不能为空");
        if (hi == null) throw new IllegalArgumentException("key不能为空");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i=rank(lo);i<rank(hi);i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) queue.enqueue(hi);

        return queue;
    }

    @Override
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("表为空，无法调用");
        return keys[0];
    }

    @Override
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("表为空，无法调用");
        return keys[n-1];
    }

    @Override
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        int k=rank(key);
        if (k<n && key.compareTo(keys[k]) == 0) return keys[k];  // 命中

        // 未命中
        if (k==0) return null;
        else return keys[k-1];
    }

    @Override
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        int k=rank(key);
        if (k==n) return null;
        else return keys[k];
    }

    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        // 二分查找
        int lo=0, hi=n-1;
        while (lo<=hi) {
            int mid=lo+(hi-lo)/2;
            int cmp=key.compareTo(keys[mid]);
            if (cmp<0) hi=mid-1;
            else if (cmp>0) lo=mid+1;
            else return mid;  // 命中
        }
        return lo;  // 未命中
    }

    @Override
    public Key select(int k) {
        if (k<0 || k>=size()) throw new IllegalArgumentException("key值无效");

        return keys[k];
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("表为空，无法调用");
        delete(min());
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("表为空，无法调用");
        delete(max());
    }

    public static void main(String[] args) {
        BinarySearchST<Integer,String> st=new BinarySearchST<>();
        st.put(2,"a");
        st.put(4,"b");
        st.put(1,"c");
        st.put(7,"d");

        for (int s:st.keys()) {
            System.out.println(s+"  "+st.get(s));
        }
    }
}
