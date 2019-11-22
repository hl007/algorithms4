package graph;

import edu.princeton.cs.algs4.Stack;


// 深度优先搜索，找到所有起点为s的路径
public class DepthFirstPaths {
    private boolean[] marked;  // 顶点是否被标记
    private int[] edgeTo;  // edgeTo[v]=previous s-v路径上的最后一个边
    private int s;  // 起始顶点

    public DepthFirstPaths(Graph g,int s) {
        this.s=s;
        marked=new boolean[g.V()];
        edgeTo=new int[g.V()];
        validateVertex(s);
        dfs(g,s);
    }

    public void dfs(Graph g,int v){
        marked[v]=true;
        for (int i:g.adj(v)) {
            if (! marked[i]) {
                edgeTo[i]=v;
                dfs(g,i);
            }
        }
    }

    // 是否存在s到v的路径
    private boolean hasPathto(int v) {
        validateVertex(v);
        return marked[v];
    }

    // s到v的路径，如果不存在返回null
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathto(v)) return null;

        Stack<Integer> path=new Stack<>();  // 使用栈，后进先出
        for (int i=v;i!=s;i=edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V=marked.length;
        if (v<0 || v>=V) throw new IllegalArgumentException("顶点v只能在0~"+(V-1)+"之间");
    }

    public static void main(String[] args) {
        Graph g=new Graph(8);
        g.addEdge(1,3);
        g.addEdge(2,3);
        g.addEdge(5,4);
        g.addEdge(5,6);
        g.addEdge(4,3);
        g.addEdge(0,1);
        g.addEdge(7,6);

        System.out.println(g);

        int s=5;
        DepthFirstPaths path=new DepthFirstPaths(g,s);

        for (int v=0;v<g.V();v++) {
            if (path.hasPathto(v)) {
                System.out.printf("%d to %d: ",s,v);
                for (int x:path.pathTo(v)) {
                    if (x==s) System.out.print(x);
                    else System.out.print("-"+x);
                }
                System.out.println();
            }
            else {
                System.out.printf("%d to %d: not connected\n",s,v);
            }
        }
    }
}
