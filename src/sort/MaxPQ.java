package sort;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;//存储堆的数组
    private int N=0;//堆大小

    public MaxPQ(int maxN){
        pq=(Key[]) new Comparable[maxN+1];//数组大小
    }

    public boolean isEmpty(){
        return N==0;
    }//判断堆是否为空

    public int size(){
        return N;
    }//返回堆的大小

    public void insert(Key v){//插入元素
        pq[++N]=v;
        swim(N);//元素上浮
    }

    public Key delMax(){//删除最大元素
        Key max=pq[1];
        exch(1,N--);//将堆中最后一个元素与顶端元素互换
        pq[N+1]=null;//防止对象游离
        sink(1);//元素下沉
        return max;
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i,int j){
        Key t=pq[i];pq[i]=pq[j];pq[j]=t;
    }

    private void swim(int k){//当某节点变得比父节点大，需要由下至上的堆有序化，上浮
        while(k>1 && less(k/2,k)){
            exch(k/2,k);
            k=k/2;
        }
    }

    private void sink(int k){
        while(2*k<=N){//当某节点变得比子节点小，需要由上自下的堆有序化，下沉
            int j=2*k;//左子节点
            if(j<N && less(j,j+1)) j++;//j表示子节点中较大节点索引
            if(!less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }
}
