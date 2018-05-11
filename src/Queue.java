import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Queue<Item> {
    private Node first; //指向最早添加的结点的链接,队头
    private Node last; //指向最近添加的结点的链接，队尾
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item item){
        //向表尾添加元素
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()) first=last;
        else oldlast.next=last;
        N++;
    }
    public Item dequeue(){
        Item item=first.item;
        first=first.next;
        if(isEmpty()) last=null;
        N--;
        return item;
    }
    public static void main(String[] args){
        Queue<String> q=new Queue<>();
        while (!StdIn.isEmpty()){
            String item=StdIn.readString();
            if(!item.equals("-"))
                q.enqueue(item);
            else if(!q.isEmpty()) StdOut.print(q.dequeue()+" ");
        }
        StdOut.println("("+q.size()+" left on queue)");
    }
}
