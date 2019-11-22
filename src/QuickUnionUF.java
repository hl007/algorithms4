import java.awt.desktop.SystemSleepEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickUnionUF {
    private int[] id;//以触点作为索引，数组id元素为同一分量中另一个触点名称（可能自己），这种联系称为链接
    private int count;//分量数量
    public QuickUnionUF(int N){//初始化分量数组
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
    public int find(int p){//根触点即为分量标识符
        while (p!=id[p]){
            p=id[p];
        }
        return p;
    }
    public void union(int p,int q){//quick-union,
        int pRoot=find(p);
        int qRoot=find(q);

        if(pRoot==qRoot) return;

        id[pRoot]=qRoot;//重命名含有p的分量

        //合并完分量数量减一
        count--;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("F:\\web开发\\算法\\算法4\\algorithms4-study\\algs4-data\\tinyUF.txt"));
        int N= Integer.parseInt(s.next());
        QuickUnionUF uf=new QuickUnionUF(N);
        while (s.hasNext()){
            int p=Integer.parseInt(s.next());
            int q=Integer.parseInt(s.next());
            System.out.println(p+" "+q);
            if(uf.connected(p,q)) continue;
            uf.union(p,q);
        }

        System.out.println(uf.count()+"components");
    }
}
