package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

// Dijkstra算法，寻找加权有向图的单点最短路径（权重不能为负）
public class DijkstraSP {
    private double[] distTo;  // distTo[v] 起点s到v的最短距离
    private DirectedEdge[] edgeTo;  // edgeTo[v] s->v最短路径上的最后一条边
    private IndexMinPQ<Double> pq;  // 保存需要放松的顶点

    public DijkstraSP(EdgeWeightedDiGraph g,int s) {
        for (DirectedEdge e:g.edges()) {
            if (e.weight()<0) throw new IllegalArgumentException("边"+e+"的权重不能为负");
        }

        distTo=new double[g.V()];
        edgeTo=new DirectedEdge[g.V()];

        validateVertex(s);

        // 初始，s到其余顶点的距离为正无穷，s到自己的距离为0
        for (int v=0;v<g.V();v++) {
            distTo[v]=Double.POSITIVE_INFINITY;
        }
        distTo[s]=0.0;

        pq=new IndexMinPQ<>(g.V());
        pq.insert(s,distTo[s]);
        while (!pq.isEmpty()) {
            int v=pq.delMin();
            for (DirectedEdge e:g.adj(v)) {
                relax(e);
            }
        }

    }

    // 放松一条边v->w，即s到v和v到w的距离之和是否比起点s到w的距离小，如果小，即此边有效，否则此边无效
    private void relax(DirectedEdge e) {
        int v=e.from();
        int w=e.to();
        if (distTo[v]+e.weight()<distTo[w]) {
            distTo[w]=distTo[v]+e.weight();
            edgeTo[w]=e;
            if (pq.contains(w)) pq.decreaseKey(w,distTo[w]);  // 更新
            else                pq.insert(w,distTo[w]);  // 插入
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path=new Stack<>();
        for (DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        int V=distTo.length;
        if (v<0 || v>=V) throw new IllegalArgumentException("顶点v只能在0~"+(V-1)+"之间");
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDiGraph g = new EdgeWeightedDiGraph(in);
        DijkstraSP sp = new DijkstraSP(g, 0);
        for (int v=0;v<g.V();v++) {
            if (sp.hasPathTo(v)) {
                System.out.print(String.format("%s to %s:  ", 0,v));
                for (DirectedEdge e : sp.pathTo(v)) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }
        }
    }

}
