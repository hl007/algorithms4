package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;


// 符号图，顶点为字符串
// 实现：在类Graph的实现上，加一个顶点和索引的映射
public class SymbolGraph {
    private ST<String,Integer> st;  // 符号表，顶点名->索引
    private String[] keys;  // 索引->顶点名
    private Graph g;  // 图

    // 从文件中读入一幅图，每行顶点由分隔符分隔；
    public SymbolGraph(String filename,String delimiter) {
        // 第一次读取文件
        In in=new In(filename);

        // 先构造st
        st=new ST<>();
        while (!in.isEmpty()) {
            String[] a=in.readLine().split(delimiter);
            for (int i=0;i<a.length;i++) {
                if (! st.contains(a[i])) {
                    st.put(a[i],st.size());
                }
            }
        }

        // 再构造keys
        keys=new String[st.size()];
        for (String key:st.keys()) {
            keys[st.get(key)]=key;
        }

        // 第二次读取文件
        in=new In(filename);
        g=new Graph(st.size());
        // 构造图
        while (!in.isEmpty()) {
            String[] a;
            a = in.readLine().split(delimiter);
            int v=st.get(a[0]);
            for (int i=1;i<a.length;i++) {
                int w=st.get(a[i]);
                g.addEdge(v,w);
            }
        }
    }

    public Graph graph() {
        return g;
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int i) {
        return keys[i];
    }

    public static void main(String[] args) {
        String filename=args[0];
        String delimiter=args[1];

        SymbolGraph sg=new SymbolGraph(filename,delimiter);
        Graph g=sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : g.adj(s)) {
                    System.out.println("   " + sg.name(v));
                }
            }
            else {
                System.out.println("input not contain '" + source + "'");
            }
        }
    }

}
