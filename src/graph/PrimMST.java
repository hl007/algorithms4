package graph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.In;

// 加权无向图的最小生成树（prim算法，即时实现）
public class PrimMST {
    private boolean[] marked;  // 最小生成树的顶点
    private Edge[] edgeTo;  // edgeTo[v] 顶点v所有切边中权重最小的横切边
    private Double[] distTo;  // distTo[v] 顶点v所有切边中权重最小的横切边的权重
    private IndexMinPQ<Double> pq;  // 索引优先队列，索引为顶点，值为顶点的权重

    public PrimMST(EdgeWeightedGraph g) {
        marked=new boolean[g.V()];
        edgeTo=new Edge[g.V()];
        distTo=new Double[g.V()];
        pq=new IndexMinPQ<>(g.V());
        for (int v=0;v<g.V();v++) {
            distTo[v]=Double.POSITIVE_INFINITY;  // 初始化时，顶点与树的距离（权重）无穷远
        }

        for (int s=0;s<g.V();s++) {  // 生成最小生成树或最小生成森林
            if (!marked[s]) {
                prim(g,s);
            }
        }
    }

    private void prim(EdgeWeightedGraph g,int s) {
        distTo[s]=0.0;  // 一棵树的起始顶点
        pq.insert(s,distTo[s]);
        while (!pq.isEmpty()) {
            int v=pq.delMin();
            scan(g,v);  // 进行一次新的切分
        }
    }

    private void scan(EdgeWeightedGraph g,int v){
        marked[v]=true;
        for (Edge e:g.adj(v)) {
            int w=e.other(v);  // 顶点v的相邻顶点

            if (marked[w]) continue;  // v-w均在树中
            if (e.weight()<distTo[w]) {  // 找到了权重更小的横切边
                distTo[w]=e.weight();
                edgeTo[w]=e;
                if (pq.contains(w)) pq.decreaseKey(w,distTo[w]);  // 更新队列
                else pq.insert(w, distTo[w]);  // 插入队列
            }
        }
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        EdgeWeightedGraph g=new EdgeWeightedGraph(in);
        PrimMST mst=new PrimMST(g);
        for (Edge e:mst.edgeTo) {
            System.out.println(e);
        }
        for (Double i:mst.distTo) {
            System.out.println(i);
        }
    }
}
