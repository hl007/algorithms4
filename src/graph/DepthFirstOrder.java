package graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

// 有向图中的顶点排序（基于深度优搜索）
public class DepthFirstOrder {
    private boolean[] marked;  // 顶点是否可达
    private Queue<Integer> pre;  // 所有顶点的前序排列
    private Queue<Integer> post;  // 所有顶点的后序排列
    private Stack<Integer> reversePost;  // 所有顶点的逆后续排列（有向无环图的拓扑顺序）

    public DepthFirstOrder(DiGraph g) {
        pre=new Queue<>();
        post=new Queue<>();
        reversePost=new Stack<>();
        marked=new boolean[g.V()];
        for (int v=0;v<g.V();v++) {
            if (!marked[v]) {
                dfs(g,v);
            }
        }
    }

    private void dfs(DiGraph g,int v) {
        marked[v]=true;
        pre.enqueue(v);  // 在递归调用之前将顶点加入队列
        for (int i:g.adj(v)) {
            if (!marked[i]) {
                dfs(g,i);
            }
        }
        post.enqueue(v);  // 在递归调用之后将顶点加入队列
        reversePost.push(v);  // // 在递归调用之后将顶点压入栈
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
