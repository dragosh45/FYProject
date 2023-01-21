package graph;
/**
Class that encapsulates an Edge in a undirected graph
 */
public class Edge {
    private Vertex xVertex;
    private Vertex yVertex;
    public Edge(Vertex xVertex, Vertex yVertex) {
        this.xVertex = xVertex;
        this.yVertex = yVertex;
    }
    public Vertex getxVertex() {
        return xVertex;
    }
    public Vertex getyVertex() {
        return yVertex;
    }
}
