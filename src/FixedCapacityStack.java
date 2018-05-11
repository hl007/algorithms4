import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item>{
    //java禁止泛型数组
    private Item[] a=(Item[]) new Object[1];
    //栈中元素数量
    private int N=0;
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void push(Item item){
        if(N==a.length) resize(2*a.length);
        a[N++]=item;
    }
    public Item pop(){
        Item item=a[--N];
        //避免对象游离
        a[N]=null;
        if (N>0 && N==a.length/4) resize(a.length/2);
        return item;
    }

    //调整数组大小
    public void resize(int max){
        Item[]  temp=(Item[]) new Object[max];
        for(int i=0;i<N;i++)
            temp[i]=a[i];
        a=temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    //内部类，迭代器
    private class ReverseArrayIterator implements Iterator<Item>{
        private int i=N;

        public boolean hasNext(){return  i>0;}
        public Item next(){return a[--i];}
        public void remove(){}
    }
}
