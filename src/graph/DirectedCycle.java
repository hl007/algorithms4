package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

// 在有向图中寻找有向环（基于深度优先搜索）
public class DirectedCycle {
    private boolean[] marked;  // 顶点是否可达
    private int[] edgeTo;  // 边
    private boolean[] onStack;  // 是否在递归调用栈上
    private Stack<Integer> cycle;  // 有向环中的所有顶点

    public DirectedCycle(DiGraph g) {
        marked=new boolean[g.V()];
        edgeTo=new int[g.V()];
        onStack=new boolean[g.V()];
        for (int v=0;v<g.V();v++) {
            if (!marked[v] && cycle==null) {
                dfs(g,v);
            }
        }
    }

    private void dfs(DiGraph g,int v) {
        marked[v]=true;
        onStack[v]=true;

        // 如果找到一条边v->w，而且w在栈中（说明存在一条w到v的有向路径），就找到一个环
        for (int w:g.adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w]=v;
                dfs(g,w);
            }
            else if (onStack[w]){
                cycle=new Stack<>();
                for (int x=v;x!=w;x=edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v]=false;  // v已不在调用栈中
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        DiGraph g=new DiGraph(in);
        DirectedCycle dc=new DirectedCycle(g);
        System.out.println(dc.cycle);
    }

}
