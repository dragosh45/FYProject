package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
public class Vertex {
    int label;
    public Vertex(int label) {
        this.label = label;
    }
    public int getLabel() {
        return label;
    }
    /**
     * Method used to retrieve the neighbours from a vertex
     * @param adjList
     * @return
     */
    public ArrayList<Vertex> getNeighbours (HashMap<Integer,ArrayList<Vertex>> adjList) {
        ArrayList<Vertex> neighboursList = new ArrayList<>();
        int vertexLabel = this.getLabel();
        for (Integer I : adjList.keySet()) {
            int value = I.intValue();
            if(vertexLabel == value) {
                neighboursList = adjList.get(I);
            }
        }
        return neighboursList;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return getLabel() == vertex.getLabel();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getLabel());
    }
}