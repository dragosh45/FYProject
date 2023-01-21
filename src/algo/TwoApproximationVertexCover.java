package algo;
import graph.Edge;
import graph.Vertex;
import java.util.ArrayList;
/**
 * This class contains methods to obtain a vertex cover given the maximal graph edge set
 */
public class TwoApproximationVertexCover {
    /**
     * Adds the 2 endpoints from each edge in the maximal graph set
     * @param maximalGraphMatchingSet maximal graph edge set
     * @return vertex cover set
     */
    public ArrayList<Vertex> twoApproximationVertexCover(ArrayList<Edge> maximalGraphMatchingSet, int maxVertexValue) {
        ArrayList<Vertex> minimumVertexSet = new ArrayList<>();
        int [] visited = new int[maxVertexValue+1];
        Edge edge;
        for (int i=0 ; i<maximalGraphMatchingSet.size(); i++) {
            edge = maximalGraphMatchingSet.get(i);
            if(visited[edge.getxVertex().getLabel()] == 0) {
                minimumVertexSet.add(edge.getxVertex());
            }
            if(visited[edge.getyVertex().getLabel()] == 0) {
                minimumVertexSet.add(edge.getyVertex());
            }
            visited[edge.getxVertex().getLabel()] ++;
            visited[edge.getyVertex().getLabel()] ++;
        }
        return minimumVertexSet;
    }
    /**
     *Adds 1 endpoint from each edge in the maximal graph set
     * @param maximalGraphMatchingSet maximal graph edge set
     * @return vertex cover set
     */
    public ArrayList<Vertex> twoApproximationVertexCoverOneVertex(ArrayList<Edge> maximalGraphMatchingSet,int maxVertexValue) {
        ArrayList<Vertex> minimumVertexSet = new ArrayList<>();
        int [] visited = new int[maxVertexValue+1];
        Edge edge;
        for (int i=0 ; i<maximalGraphMatchingSet.size(); i++) {
            edge = maximalGraphMatchingSet.get(i);
            if(visited[edge.getxVertex().getLabel()] == 0) {
                minimumVertexSet.add(edge.getxVertex());
            }
            visited[edge.getxVertex().getLabel()] ++;
        }
        return minimumVertexSet;
    }
    /**
     *Adds 1 endpoint from each edge in the maximal graph set
     * @param maximalGraphMatchingSet
     * @return
     */
    public ArrayList<Vertex> twoApproximationVertexCoverSecondVertex(ArrayList<Edge> maximalGraphMatchingSet,int maxVertexValue) {
        ArrayList<Vertex> minimumVertexSet = new ArrayList<>();
        int [] visited = new int[maxVertexValue+1];
        Edge edge;
        for (int i=0 ; i<maximalGraphMatchingSet.size(); i++) {
            edge = maximalGraphMatchingSet.get(i);
            if(visited[edge.getyVertex().getLabel()] == 0) {
                minimumVertexSet.add(edge.getyVertex());
            }
            visited[edge.getyVertex().getLabel()] ++;
        }
        return minimumVertexSet;
    }
}