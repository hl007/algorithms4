package graph;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

// 找出一个有向图中所有的强连通分量（基于深度优先搜索）
public class KosarajuSharirSCC {
    private boolean[] marked;  // 顶点是否被使用
    private int[] id;  // id[v] 包含v的连通分量id
    private int[] size;  // size[id] 某个连通分量的顶点数
    private int count;  // 连通分量的数量

    public KosarajuSharirSCC(DiGraph g) {
        DepthFirstOrder dfo=new DepthFirstOrder(g.reverse());
        marked=new boolean[g.V()];
        id=new int[g.V()];
        size=new int[g.V()];
        for (int v:dfo.reversePost()) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    // 深度优先搜索
    private void dfs(DiGraph g,int v) {
        marked[v]=true;
        id[v]=count;  // 第一连通分量id为0，第二个为1，依次类推
        size[count]++;
        for (int i: g.adj(v)) {
            if (!marked[i]) {
                dfs(g,i);
            }
        }
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        DiGraph g=new DiGraph(in);
        KosarajuSharirSCC cc=new KosarajuSharirSCC(g);
        System.out.println(cc.count);
        System.out.println(Arrays.toString(cc.id));
    }

}
