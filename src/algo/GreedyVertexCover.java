package algo;
import graph.Graph;
import graph.Vertex;
import java.util.*;
/**
 * This algorithm picks the max incidence vertex from graph and constructs a vertex cover
 * starting from the max incidence vertex.
 */
public class GreedyVertexCover {
    /**
     * This method returns a vertex cover list
     * @param graph - the graph input for the algorithm
     * @return vertex cover list
     */
    public ArrayList<Vertex> greedyVertexCover(int maxVertexValue,Graph graph) {
        HashMap<Integer,ArrayList<Vertex>> adjList = graph.getAdjList();
        ArrayList<Vertex> greedyVertexCover = new ArrayList<Vertex>();
        Iterator iter = adjList.entrySet().iterator();
        Queue<ArrayList<Vertex>> myQueue = new LinkedList<>();
        int [] visited = new int[maxVertexValue+1];
        int [] incidence = new int[maxVertexValue+1];
        int maxIncidenceVertex = 0, max = 0;
        for (Integer I : adjList.keySet()) {
            for (int i = 0; i < adjList.get(I).size(); i++) {
                incidence[adjList.get(I).get(i).getLabel()]++;
            }
        }
        for (int j = 0 ; j<incidence.length; j++) {
          if(incidence[j] > max) {
              max = incidence[j];
              maxIncidenceVertex = j;
            }
        }
        while (iter.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)iter.next();
            if (((int) pair.getKey() != maxIncidenceVertex)) {
                myQueue.add((ArrayList<Vertex>) pair.getValue());
            }
            else {
                ArrayList<Vertex> maxIncidenceVertexList = (ArrayList<Vertex>) pair.getValue();
                greedyVertexCover.add(maxIncidenceVertexList.get(0));
                for(int x = 0 ; x < maxIncidenceVertexList.size() ; x++) {
                    visited[maxIncidenceVertexList.get(x).getLabel()] ++;
                }
            }
        }
        while(!myQueue.isEmpty()) {
            ArrayList<Vertex> myList = myQueue.remove();
            for(int i = 0 ; i< myList.size() ; i++) {
                if(visited[myList.get(i).getLabel()] == 0){
                    visited[myList.get(i).getLabel()] ++;
                    greedyVertexCover.add(myList.get(i));
                }
            }
        }
        return greedyVertexCover;
    }
}
