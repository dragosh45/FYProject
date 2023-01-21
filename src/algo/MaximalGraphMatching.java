package algo;
import graph.Vertex;
import graph.Graph;
import graph.Edge;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class contains a method for finding a maximal matching set
 */
public class MaximalGraphMatching {
    /**
     * This method finds a maximal matching edge set by greedily picking edges from the edge list that have not been
     * visited and adds them to a set.
     * @param edgeSet Graph edges set
     * @param graph
     * @return maximal matching edges set
     */
    public ArrayList<Edge> maximalGraphMatching(int maxValuedVertex, ArrayList<Edge> edgeSet,Graph graph) {
        HashMap<Integer,ArrayList<Vertex>> adjList = graph.getAdjList();
        ArrayList<Edge> maximalMatchingSet = new ArrayList<>();
        Edge edge;
        int visitedSize = maxValuedVertex+1, index = 0;
        int [] visited = new int[visitedSize];
        while(index<edgeSet.size()) {
            edge = edgeSet.get(index);
            index ++;
            if(visited[edge.getyVertex().getLabel()] == 0 || visited[edge.getxVertex().getLabel()] == 0) {
                visited[edge.getyVertex().getLabel()]++;
                visited[edge.getxVertex().getLabel()]++;
                maximalMatchingSet.add(edge);
            }
            ArrayList<Vertex> vertexNeighbours;
            vertexNeighbours = edge.getxVertex().getNeighbours(adjList);
            for (int j = 0 ; j<vertexNeighbours.size() ; j++) {
                if(maximalMatchingSet.contains(edge)) {
                    visited[vertexNeighbours.get(j).getLabel()]++;
                }
            }
            ArrayList<Vertex> verteyNeighbours;
            verteyNeighbours = edge.getyVertex().getNeighbours(adjList);
            for (int j = 0 ; j<verteyNeighbours.size() ; j++) {
                if(maximalMatchingSet.contains(edge)) {
                    visited[verteyNeighbours.get(j).getLabel()]++;
                }
            }
        }
        return maximalMatchingSet;
    }
}
