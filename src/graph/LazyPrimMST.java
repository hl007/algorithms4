package graph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;

// 加权无向图的最小生成树（prim算法，延时实现）
public class LazyPrimMST {
    private double weight;  // 最小生成树的权重
    private boolean[] marked;  // 最小生成树的顶点
    private Queue<Edge> mst;  // 最小生成树的边
    private MinPQ<Edge> pq;  // 优先队列，保存切分产生的横切边（包括已经失效的横切边）

    public LazyPrimMST(EdgeWeightedGraph g) {
        marked=new boolean[g.V()];
        mst=new Queue<>();
        pq=new MinPQ<>();
        for (int v=0;v<g.V();v++) {  // 如果该图是完全连通的，则得到一个该图的最小生成树；否则得到的是最小生成森林
            if (!marked[v]) {
                prim(g,v);
            }
        }
    }

    private void prim(EdgeWeightedGraph g,int s) {
        scan(g,s);
        while (!pq.isEmpty()) {
            Edge e=pq.delMin();  // 获取横切边中权重最小的边
            int v=e.either();
            int w=e.other(v);
            if (marked[v] && marked[w]) continue;  // 延迟（不删除），两个点已经在树中
            else {
                mst.enqueue(e);  // 找到一条边
                weight += e.weight();
                if (!marked[v]) scan(g,v);  // 进行一次新的切分
                if (!marked[w]) scan(g,w);
            }
        }
    }

    // 访问一个顶点
    private void scan(EdgeWeightedGraph g,int v) {
        assert !marked[v];
        marked[v]=true;  // 将该顶点放入树中
        for (Edge edge:g.adj(v)) {  // 将该顶点的邻接表中的所有边加入（不包含已经在树中的）
            if (!marked[edge.other(v)]) pq.insert(edge);
        }
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        EdgeWeightedGraph g=new EdgeWeightedGraph(in);
        LazyPrimMST mst=new LazyPrimMST(g);
        for (Edge edge:mst.mst) {
            System.out.println(edge);
        }
    }

}
