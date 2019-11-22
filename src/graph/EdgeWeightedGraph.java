package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

// 加权无向图
public class EdgeWeightedGraph {
    private int V;  // 顶点数
    private int E;  // 边数
    private Bag<Edge>[] adj;  // 邻接表数组，数组元素为边

    // 创建含有含有V个顶点但不含边的图
    public EdgeWeightedGraph(int V) {
        if (V<0) throw new IllegalArgumentException("顶点数不能小于0");

        this.V=V;
        this.E=0;
        adj=(Bag<Edge>[]) new Bag[V];
        for (int i=0;i<V;i++) {
            adj[i]=new Bag<>();
        }
    }

    // 从标准输入流中读入一幅图
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("边数必须是非负数");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            validateVertex(v);
            validateVertex(w);
            addEdge(new Edge(v,w,weight));
        }
    }

    // 顶点数
    public int V() {
        return V;
    }

    // 边数
    public int E() {
        return E;
    }

    // 增加一条边
    public void addEdge(Edge e) {
        int v=e.either();
        int w=e.other(v);

        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    // 和v相邻的所有边
    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // 返回图中的所有边
    public Iterable<Edge> edges() {
        ArrayList<Edge> list=new ArrayList<>();
        for (int v=0;v<V();v++) {
            for (Edge e:adj(v)) {
                if (! list.contains(e)) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    // 对象的字符串表示
    public String toString() {
        String myStr="";
        for (int i=0;i<V;i++) {
            String tempStr=i+":";
            for (Edge e:adj[i]) {
               tempStr+=" "+e;
            }
            myStr+=tempStr+"\n";
        }
        return myStr;
    }

    private void validateVertex(int v) {
        if (v<0 || v>=V) throw new IllegalArgumentException("顶点v只能在0~"+(V-1)+"之间");
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        EdgeWeightedGraph g=new EdgeWeightedGraph(in);
        System.out.println(g);
        for (Edge edge: g.edges()) {
            System.out.println(edge);
        }
    }
}
