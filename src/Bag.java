import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int size;
    private class Node{
        Item item;
        Node next;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public void  add(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        size++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current=first;
        @Override
        public boolean hasNext() {
            return current==null;
        }
        @Override
        public void remove() { }
        @Override
        public Item next() {
            Item item=first.item;
            current=first.next;
            return item;
        }
    }
}
