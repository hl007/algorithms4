package search;

// 散列表（基于线性探测法）
public class LinearProbingHashST<Key,Value> implements DisorderedSymbolTable<Key,Value>{
    private static final int INIT_CAPACITY=4;

    private int n;  // 键值对数目
    private int m;  // 数组大小
    private Key[] keys;
    private Value[] vals;

   public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

   public LinearProbingHashST(int m) {
       this.m=m;
       keys=(Key[]) new Object[m];
       vals=(Value[])new Object[m];
   }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");

        for (int i=hash(key);keys[i] != null;i=(i+1)%m) {
            if (keys[i].equals(key)){
                return vals[i];
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (val == null) {
            delete(key);
            return;
        }

        // 如果数组满二分之一，则扩容
        if (n>=m/2) resize(2*m);

        int i;
        for (i=hash(key);keys[i]!=null;i=(i+1)%m) {
            if (keys[i].equals(key)) {  // 命中
                vals[i] = val;
                return;
            }
        }
        // 遇到空位，插入
        keys[i]=key;
        vals[i]=val;
        n++;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (!contains(key)) return;

        // 找到key的位置
        int i=hash(key);
        if (!key.equals(keys[i])) {
            i=(i+1)%m;
        }
        // 删除
        keys[i]=null;
        vals[i]=null;

        i=(i+1)%m;
        while (keys[i]!=null) {
            Key nextKey=keys[i];
            Value nextVal=vals[i];

            // 删除后面键簇的键值对，重新插入
            keys[i]=null;
            vals[i]=null;
            n--;
            put(nextKey,nextVal);
            i=(i+1)%m;
        }

        // 键值对过少，减小数组容量
        if (n<m/8) resize(n/2);

    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
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
        return null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
       LinearProbingHashST<Key,Value> temp=new LinearProbingHashST<>(capacity);
       for (Key key:keys) {
           temp.put(key, get(key));
       }
       keys=temp.keys;
       vals=temp.vals;
       m=temp.m;
    }

    public static void main(String[] args) {
        LinearProbingHashST<Integer,String> st=new LinearProbingHashST<>();
        st.put(2,"a");
        st.put(4,"b");
        st.put(3,"c");
        st.put(2,"d");
        for (Integer s:st.keys()) {
            System.out.println(s+" "+st.get(s));
        }
    }
}
