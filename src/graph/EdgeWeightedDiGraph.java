package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

// 加权有向图
public class EdgeWeightedDiGraph {
    private int V;  // 顶点数
    private int E;  // 边数
    private Bag<DirectedEdge>[] adj;  // 邻接表数组，数组元素为边

    // 创建含有含有V个顶点但不含边的图
    public EdgeWeightedDiGraph(int V) {
        if (V<0) throw new IllegalArgumentException("顶点数不能小于0");

        this.V=V;
        this.E=0;
        adj=(Bag<DirectedEdge>[]) new Bag[V];
        for (int i=0;i<V;i++) {
            adj[i]=new Bag<>();
        }
    }

    // 从标准输入流中读入一幅图
    public EdgeWeightedDiGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("边数必须是非负数");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            validateVertex(v);
            validateVertex(w);
            addEdge(new DirectedEdge(v,w,weight));
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
    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    // 和v相邻的所有边
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // 返回图中的所有边
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag=new Bag<>();
        for (int v=0;v<V();v++) {
            for (DirectedEdge e:adj(v)) {
                bag.add(e);
            }
        }
        return bag;
    }

    // 对象的字符串表示
    public String toString() {
        String myStr="";
        for (int i=0;i<V;i++) {
            String tempStr=i+":";
            for (DirectedEdge e:adj[i]) {
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
        EdgeWeightedDiGraph g=new EdgeWeightedDiGraph(in);
        for (DirectedEdge edge: g.edges()) {
            System.out.println(edge);
        }
    }
}
