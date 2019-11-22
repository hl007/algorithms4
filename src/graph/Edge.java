package graph;

// 加权边，用于实现加权无向图
public class Edge implements Comparable<Edge> {
    private int v;  // 一个顶点
    private int w;  // 另一个顶点
    private double weight;  // 边的权重

    public Edge(int v,int w,double weight) {
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    // 返回任意一个顶点
    public int either() {
        return v;
    }

    // 返回一个顶点的另一个顶点
    public int other(int x) {
        if (x==v) return w;
        else if (x==w) return v;
        else throw new IllegalArgumentException("该参数无效");
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight,that.weight);
    }

    public String toString() {
        return String.format("%d-%d %s",v,w,weight);
    }
}
