import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    public WeightedQuickUnionUF(int N){
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
        sz=new int[N];
        for(int i=0;i<N;i++){
            sz[i]=1;
        }
    }
    public int count(){
        return count;
    }//返回连通分量的数目
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }//判断p,q是否相连
    public int find(int p) {//根触点即为分量标识符
        while (p!=id[p]){
            p=id[p];
        }
        return p;
    }
    public void union(int p,int q){
        int i=find(p);
        int j=find(q);

        if(i==j) return;

        //将小树的根节点连接到大树的根节点
        if(sz[i]<sz[j]){
            id[i]=j;
            sz[j]+=sz[i];
        }
        else{
            id[j]=i;
            sz[i]+=sz[j];
        }

        count--;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("F:\\algorithms4\\algs4-data\\algs4-data\\largeUF.txt"));
        int N= Integer.parseInt(s.next());
        WeightedQuickUnionUF uf=new WeightedQuickUnionUF(N);
        while (s.hasNext()){
            int p=Integer.parseInt(s.next());
            int q=Integer.parseInt(s.next());
            if(uf.connected(p,q)) continue;
            uf.union(p,q);
            System.out.println(p+" "+q);
        }

        System.out.println(uf.count()+"components");
    }
}
