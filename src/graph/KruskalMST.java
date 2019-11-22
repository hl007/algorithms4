package graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.In;

// 加权无向图的最小生成树（kruskal算法）
// 原理：将所有边按权重从小到大排序处理，将边加入最小生成树中，加入的边不能成环，直到树中含有V-1条边
public class KruskalMST {

    Queue<Edge> mst;  // 最小生成树

    public KruskalMST(EdgeWeightedGraph g) {
        mst=new Queue<>();
        MinPQ<Edge> pq=new MinPQ<>();  // 将所有边排序
        for (Edge e:g.edges()) {
            pq.insert(e);
        }

        QuickFindUF uf=new QuickFindUF(g.V());  // 用于判断加入的边是否成环
        for (Edge e:pq) {
            int v=e.either();
            int w=e.other(v);
            if (!uf.connected(v,w)) {
                mst.enqueue(e);
                uf.union(v,w);
            }
        }
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        EdgeWeightedGraph g=new EdgeWeightedGraph(in);
        KruskalMST mst=new KruskalMST(g);

        for (Edge e:mst.mst) {
            System.out.println(e);
        }

    }
}
