package algo;
import graph.Graph;
import graph.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * This algorithm is an improvement for the TwoApproximationVertexCover :
 * it only chooses 1 endpoint from an edge that is in the maximal match edge set
 * adds it to the cover ; decides if other vertexes need to be part of the cover
 * and adds them to the cover
 */
public class HeuristicVertexCover {
    /**
     * Checking if a vertex has neighbour a certain vertex
     * @param graph - input graph
     * @param vertexY - vertex to get neighbours from and check if vertexToCheck is a neighbour
     * @param vertexToCheck - vertex to check if in the neighbour list
     * @return true if vertexToCheck is adjacent with vertexY or it's neighbours
     *         false if vertexToCheck is not adjacent with vertexY or any of it's neighbours
     */
    public boolean checkIfNeighbour(Graph graph,Vertex vertexY, Vertex vertexToCheck) {
        HashMap<Integer,ArrayList<Vertex>> adjList = graph.getAdjList();
        ArrayList<Vertex> neighbourList = new ArrayList<>();
        for (Integer I : adjList.keySet()) {
            if(vertexY.getLabel() == I.intValue()) {
                neighbourList = adjList.get(I);
            }
        }
        for(int i = 0 ; i< neighbourList.size() ; i++) {
            if(vertexToCheck.getLabel() == neighbourList.get(i).getLabel()) {
                return true;
            }
        }
        return false;
    }
    /**
     * This method adds the X endpoint from the edges in the maximal edges set to the cover
     * Takes the vertexes in the graph that are not in the maximal edge set and that are not
     * adjacent with any of the Y endpoints from the edges in the maximal edges set;
     * Adds this vertexes to the cover;
     *
     * @param graphVertexSet - set of all graph vertexes
     * @param maximalGraphMatchingVertexXSet - set of maximal matching vertexes from x endpoint side
     * @param maximalGraphMatchingYSet - set of maximal matching vertexes from y endpoint side
     * @param maximalGraphMatchingXYSet - full set of maximal matching vertexes
     * @param graph - graph input
     * @return the vertex cover set
     */
    public ArrayList<Vertex> heuristicVertexCover (ArrayList<Vertex> graphVertexSet, ArrayList<Vertex> maximalGraphMatchingVertexXSet, ArrayList<Vertex> maximalGraphMatchingYSet,ArrayList<Vertex> maximalGraphMatchingXYSet,Graph graph) {
        ArrayList<Vertex> heuristicVertexCoverSet = new ArrayList<>();
        HashMap<Integer,ArrayList<Vertex>> adjList = graph.getAdjList();
        int [] visited = new int[adjList.size()+1];
        heuristicVertexCoverSet.addAll(maximalGraphMatchingVertexXSet);
        graphVertexSet.removeAll(maximalGraphMatchingXYSet);
        for (int i = 0; i< graphVertexSet.size();i++) {
            for(int j = 0; j< maximalGraphMatchingYSet.size();j++) {
                if(checkIfNeighbour(graph, maximalGraphMatchingYSet.get(j), graphVertexSet.get(i)) && visited[graphVertexSet.get(i).getLabel()] == 0) {
                    visited[graphVertexSet.get(i).getLabel()] ++;
                    heuristicVertexCoverSet.add(graphVertexSet.get(i));
                }
            }
        }
        return heuristicVertexCoverSet;
    }
}