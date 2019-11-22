package graph;


import edu.princeton.cs.algs4.Bag;

// 深度优先搜索，找到起点s可到达的所有顶点
// 原理：在访问一个顶点时，将该顶点标记成已访问，再递归访问所有没有被标记过的邻居顶点
public class DirectedDFS {
    private boolean[] marked;  // 顶点是否能到达（顶点能够到达自己）
    private int count;  // 起点s能够到达的所有顶点数

    public DirectedDFS(DiGraph g, int s) {
        marked=new boolean[g.V()];
        validateVertex(s);
        dfs(g,s);
    }

    // source中所有顶点可达的所有顶点
    public DirectedDFS(DiGraph g, Iterable<Integer> source) {
        marked=new boolean[g.V()];
        for (Integer s:source) {
            dfs(g,s);
        }
    }

    public void dfs(DiGraph g,int v){
        count++;
        marked[v]=true;
        for (int i:g.adj(v)) {
            if (! marked[i]) {
                dfs(g,i);
            }
        }
    }

    // 顶点v能否到达
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int count() {
        return count;
    }

    private void validateVertex(int v) {
        int V=marked.length;
        if (v<0 || v>=V) throw new IllegalArgumentException("顶点v只能在0~"+(V-1)+"之间");
    }

    public static void main(String[] args) {
        DiGraph g=new DiGraph(8);
        g.addEdge(1,3);
        g.addEdge(2,3);
        g.addEdge(5,4);
        g.addEdge(5,6);
        g.addEdge(4,3);
        g.addEdge(0,1);
        g.addEdge(7,6);

        System.out.println(g);
        Bag<Integer> source=new Bag<>();
        source.add(1);
        source.add(2);
        DirectedDFS search=new DirectedDFS(g,source);
        for (boolean b:search.marked) {
            System.out.println(b);
        }
    }
}
