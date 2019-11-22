package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

// 广度优先搜索，寻找起点s到顶点v的最短路径
// 原理：寻找s到v的最短路径，先在所有一条边能够到达的顶点中找，找不到再在所有两条边能够到达的顶点中找...
public class BreadthFirstPaths {
    private static final int INFINITY= Integer.MAX_VALUE;
    private boolean[] marked;  // 顶点是否被标记
    private int[] edgeTo;  // edgeTo[v]=previous s-v最短路径上的最后一个边
    private int[] distTo;  // distTo[v]=number s-v最短路径上边的数量

    public BreadthFirstPaths(Graph g,int s) {
        marked=new boolean[g.V()];
        edgeTo=new int[g.V()];
        distTo=new int[g.V()];
        validateVertex(s);
        bfs(g,s);
    }

    public void bfs(Graph g,int s) {
        Queue<Integer> q=new Queue<>();  // 队列用于储存待搜索的顶点
        for (int i=0;i<g.V();i++) {
            distTo[i]=INFINITY;  // 初始化
        }
        distTo[s]=0;
        marked[s]=true;
        q.enqueue(s);  // 入列

        while(!q.isEmpty()) {  // 如果队列不为空，则取出一个顶点进行访问
            int x=q.dequeue();  // 出列
            for (int i:g.adj(x)) {
                if (!marked[i]) {
                    q.enqueue(i);
                    edgeTo[i]=x;
                    marked[i]=true;
                    distTo[i]=distTo[x]+1;
                }
            }
        }
    }

    // 是否存在s到v的路径
    private boolean hasPathto(int v) {
        validateVertex(v);
        return marked[v];
    }

    // s到v的最短路径，如果不存在返回null
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathto(v)) return null;

        Stack<Integer> path=new Stack<>();  // 使用栈，后进先出
        int i;
        for (i=v;distTo[i]!=0;i=edgeTo[i]) {
            path.push(i);
        }
        path.push(i);
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
        BreadthFirstPaths path=new BreadthFirstPaths(g,s);

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
