package graph;

// 深度优先搜索，找到与起点s连通的所有顶点
// 原理：在访问一个顶点时，将该顶点标记成已访问，再递归访问所有没有被标记过的邻居顶点
public class DepthFirstSearch {
    private boolean[] marked;  // 顶点是否被标记
    private int count;  // 与起点s连通的所有顶点数

    public DepthFirstSearch(Graph g,int s) {
        marked=new boolean[g.V()];
        validateVertex(s);
        dfs(g,s);
    }

    public void dfs(Graph g,int v){
        count++;
        marked[v]=true;
        for (int i:g.adj(v)) {
            if (! marked[i]) {
                dfs(g,i);
            }
        }
    }

    // 顶点v是否被标记
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
        Graph g=new Graph(8);
        g.addEdge(1,3);
        g.addEdge(2,3);
        g.addEdge(5,4);
        g.addEdge(5,6);
        g.addEdge(4,3);
        g.addEdge(0,1);
        g.addEdge(7,6);

        System.out.println(g);
        DepthFirstSearch search=new DepthFirstSearch(g,5);
        for (boolean b:search.marked) {
            System.out.println(b);
        }
    }
}
