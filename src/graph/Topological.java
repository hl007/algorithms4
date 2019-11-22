package graph;

import edu.princeton.cs.algs4.In;

// 对有向无环图进行拓扑排序
public class Topological {
    private Iterable<Integer> order;

    public Topological(DiGraph g) {
        DirectedCycle dc=new DirectedCycle(g);
        if (! dc.hasCycle()) {
            DepthFirstOrder dfo=new DepthFirstOrder(g);
            order=dfo.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        In in=new In(args[0]);
        DiGraph g=new DiGraph(in);
        Topological t=new Topological(g);
        System.out.println(t.order());
    }
}
