package graph;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class that encapsulates a undirected graph graph
 */
public class Graph {
    HashMap<Integer, ArrayList<Vertex>> adjList;
    public Graph(HashMap<Integer, ArrayList<Vertex>> adjList) {
        this.adjList = adjList;
    }
    public HashMap<Integer, ArrayList<Vertex>> getAdjList() {
        return adjList;
    }
    public ArrayList<Vertex> getVertexSet(HashMap<Integer, ArrayList<Vertex>> adjList) {
        ArrayList<Vertex> graphVertexSetList = new ArrayList<>();
        Vertex vertex;
        for (Integer I : adjList.keySet()) {
            int value = I.intValue();
            vertex = new Vertex(value);
            graphVertexSetList.add(vertex);
        }
        return graphVertexSetList;
    }
    /**
     * Method used to retrieve the vertexes number
     * @return nr - the number of vertexes
     */
    public int getVertexesNo() {
        int nr = 0;
        for(Integer I : adjList.keySet()) {
            nr ++;
        }
        return nr;
    }
    /**
     * Method used to retrieve the edge list from the adjList
     * @param adjList
     * @return edgeList - the edge list
     */
    public ArrayList<Edge> getEdgeList(HashMap<Integer, ArrayList<Vertex>> adjList) {
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (Integer I : adjList.keySet()) {
            int value1 = I.intValue();
            Vertex x = new Vertex(value1);
            for (int i = 1; i < adjList.get(I).size(); i++) {
                int value2 = adjList.get(I).get(i).getLabel();
                Vertex y = new Vertex(value2);
                Edge e = new Edge(x,y);
                edgeList.add(e);
            }
        }
        return edgeList;
    }
    /**
     * Method used to retrieve the max numbered vertex
     * @param adjList
     * @return maxNumberedVertex - max numbered vertex value
     */
    public int getMaxNumberedVertex(HashMap<Integer, ArrayList<Vertex>> adjList) {
        int maxNumberVertex = 0;
        int value2 = 0, value1 = 0;
        for (Integer I : adjList.keySet()) {
            value1 = I.intValue();
            if(value1 > maxNumberVertex) {
                maxNumberVertex = value1;
            }
            for (int i = 0; i < adjList.get(I).size(); i++) {
                value2 = adjList.get(I).get(i).getLabel();
                if(value2 > maxNumberVertex) {
                    maxNumberVertex = value2;
                }
            }
        }
        return maxNumberVertex;
    }
}
