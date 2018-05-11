import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickFindUF {
    private int[] id;//分量id(以触点作为索引)
    private int count;//分量数量
    public QuickFindUF(int N){//初始化分量数组
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++)
            id[i]=i;
    }
    public int count(){
        return count;
    }//返回连通分量的数目
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }//判断p,q是否相连
    public int find(int p){
        return id[p];
    }//quikc-find算法，返回p所在分量的标识符
    public void union(int p,int q){//在p,q之间添加一条连接，合并p,q所在的两个分量
        int pID=find(p);
        int qID=find(q);

        if(pID==qID) return;//如果p,q所在分量的标识符一致，返回

        //如果不一致，将p所在分量id重命名为q所在分量
        for(int i=0;i<id.length;i++){
            if(id[i]==pID){
                id[i]=qID;
            }
        }

        //合并完分量数量减一
        count--;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("F:\\algorithms4\\algs4-data\\algs4-data\\tinyUF.txt"));
        int N= Integer.parseInt(s.next());
        QuickFindUF uf=new QuickFindUF(N);
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
