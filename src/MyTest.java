import edu.princeton.cs.algs4.BST;

public class MyTest {
    public static void main(String[] args){
        BST<Integer,String> bst=new BST<Integer, String>();
        bst.put(4,"a");
        bst.put(2,"b");
        bst.put(3,"c");
        bst.put(9,"d");
        bst.put(7,"e");
        bst.put(14,"f");
        bst.put(1,"g");
        bst.put(2,"w");

        for (Integer s : bst.keys()) {
            System.out.println(s + " " + bst.get(s));
        }
    }
}
