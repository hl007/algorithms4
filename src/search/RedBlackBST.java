package search;

// 红黑二叉查找树（红黑树），用标准的二叉树和一些额外的信息来表示2-3树（完全平衡）
// 红黑树中的两种链接：红链接，将两个2-结点连接起来构造成一个3-结点，黑链接，普通链接
// 红黑树中的红链接只能是做链接；每个结点最多能与一条红链接相连；所有空链接到根结点的路径上的黑链接数目相等
// 2-3查找树，树中有两种结点：2-结点（一个键，两条链接）；3-结点（两个键，3条链接）；完全平衡的2-3树的每个空链接到根结点的距离相等
public class RedBlackBST<Key extends Comparable<Key>,Value> implements OrderedSymbolTable<Key,Value> {
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left,right;
        private boolean color;  // 结点颜色，指向该结点的链接颜色（根结点为黑色）
        private int size;

        public Node(Key key,Value val,boolean color,int size) {
            this.key=key;
            this.val=val;
            this.color=color;
            this.size=size;
        }
    }

    // 左旋转，会改变红链接的指向
    // 用途：出现红色右链接或者连续的两条红链接
    private Node rotateLeft(Node h) {
        Node x=h.right;
        h.right=x.left;
        x.left=h;

        // 结点颜色更新
        x.color=h.color;
        x.left.color=RED;

        // 树的大小更新
        x.size=h.size;
        h.size=size(h.left)+size(h.right)+1;

        return x;
    }

    // 右旋转
    private Node rotateRight(Node h) {
        Node x=h.left;
        h.left=x.right;
        x.right=h;

        // 结点颜色更新
        x.color=h.color;
        x.right.color=RED;

        // 树的大小更新
        x.size=h.size;
        h.size=size(h.left)+size(h.right)+1;

        return x;
    }

    // 颜色转换
    // 用途：出现一个结点的左链接和右链接均为红链接
    private void flipColors(Node x) {
        x.left.color=!x.left.color;
        x.right.color=!x.right.color;
        x.color=!x.color;
    }


    @Override
    public Value get(Key key) {
        return get(root,key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (x==null) return null;

        int cmp=key.compareTo(x.key);
        if (cmp<0) return get(x.left,key);
        else if (cmp>0) return get(x.right,key);
        else return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key不能为空");
        if (val==null) {
            delete(key);
            return;
        }

        root=put(root,key,val);
        root.color=BLACK;  // 根节点为黑色
    }

    private Node put(Node x,Key key,Value val) {
        if (x==null) return new Node(key,val,RED,1);  // 在树底进行插入操作

        int cmp=key.compareTo(x.key);
        if (cmp<0) x.left=put(x.left,key,val);
        else if (cmp>0) x.right=put(x.right,key,val);
        else x.val=val;

        if (isRed(x.left) && isRed(x.right))         flipColors(x);  // 左右链接均为红链接（4-结点转成2-结点）
        // 以下两种情况可依次触发
        if (!isRed(x.left) && isRed(x.right))        x=rotateLeft(x);  // 左链接为黑链接，右链接为红链接（3-结点左旋转）
        if (isRed(x.left) && isRed(x.left.left))     x=rotateRight(x);  // 左链接和左结点的左链接均为红链接（4-结点右旋转为另一个4-结点）

        x.size=size(x.left)+size(x.right)+1;
        return x;
    }

    private boolean isRed(Node x) {
        if (x==null) return false; // 空链接为黑链接
        return x.color==RED;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }


    private int size(Node x) {
        if (x==null) return 0;
        else return x.size;
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

//    // 沿着左链接向下遍历，确定当前结点不是2-结点
//    private Node deleteMin(Node x) {
//
//    }
//
//    // 用途：删除时，在沿左链接向下的过程中，当前结点的左子结点是2-结点，而右子结点不是2-结点，需将右子结点的一个键移到左子结点中
//    private Node moveRedLeft(Node x) {
//
//    }

    @Override
    public void deleteMax() {

    }

    public static void main(String[] args) {
        RedBlackBST<Integer,String> bst=new RedBlackBST<>();
        bst.put(1,"a");
        bst.put(2,"b");
        bst.put(8,"c");
        bst.put(3,"d");
        System.out.println(bst.get(8));
    }
}
