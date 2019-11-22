package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

// 无向图
public class Graph {
    private int V;  // 顶点数
    private int E;  // 边数
    private Bag<Integer>[] adj;  // 邻接表数组


    // 创建含有含有V个顶点但不含边的图
    public Graph(int V) {
        if (V<0) throw new IllegalArgumentException("顶点数不能小于0");

        this.V=V;
        this.E=0;
        adj=(Bag<Integer>[]) new Bag[V];
        for (int i=0;i<V;i++) {
            adj[i]=new Bag<>();
        }
    }

    // 从标准输入流中读入一幅图
    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("顶点数必须是非负数");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("边数必须是非负数");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("输入数据无法构造图", e);
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
    public void addEdge(int v,int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    // 和v相邻的所有顶点
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // 对象的字符串表示
    public String toString() {
        String myStr="";
        for (int i=0;i<V;i++) {
            String tempStr=i+":";
            for (Integer s:adj[i]) {
               tempStr+=" "+s;
            }
            myStr+=tempStr+"\n";
        }
        return myStr;
    }

    private void validateVertex(int v) {
        if (v<0 || v>=V) throw new IllegalArgumentException("顶点v只能在0~"+(V-1)+"之间");
    }

    public static void main(String[] args) {
        Graph g=new Graph(8);
        g.addEdge(1,3);
        g.addEdge(2,3);
        g.addEdge(5,4);
        g.addEdge(5,6);
        g.addEdge(4,3);
        System.out.println(g);
    }
}
