package algo;
import graph.Graph;
import graph.Vertex;
import java.util.*;
/**
This algorithm picks a random vertex from graph and constructs a vertex cover
starting from the picked random vertex.
Returns randomVertexCover
 */
public class RandomVertexCover {
    /**
     *This method returns a vertex cover list
     * @param maxValueVertex the max incidence vertex
     * @param graph the graph from which the vertex cover is returned
     * @return randomVertexCover : the cover set to be returned
     */
    public ArrayList<Vertex> randomVertexCover(int maxValueVertex,Graph graph) {
        HashMap<Integer,ArrayList<Vertex>> adjList = graph.getAdjList();
        HashMap<Integer,ArrayList<Vertex>> adjToAppendList = new HashMap<Integer,ArrayList<Vertex>>();
        ArrayList<Vertex> randomVertexCover = new ArrayList<Vertex>();
        int [] visited = new int[maxValueVertex+1];
        Random r = new Random();
        int low = 1;
        int high = adjList.keySet().size();
        int result = r.nextInt(high-low);
        Integer I = result;
        Iterator iter = adjList.entrySet().iterator();
        while (iter.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)iter.next();
            if (((int) pair.getKey()< I)) {
                adjToAppendList.put((Integer)pair.getKey(), (ArrayList<Vertex>) pair.getValue());
                iter.remove();
            }
        }
        Queue<ArrayList<Vertex>> myQueue = new LinkedList<>();
        for (Integer K : adjList.keySet()) {
            myQueue.add(adjList.get(K));
        }
        for (Integer L : adjToAppendList.keySet()) {
            myQueue.add(adjToAppendList.get(L));
        }
        ArrayList<Vertex> firstList = myQueue.remove();
        randomVertexCover.add(firstList.get(0));
        for(int j = 0 ; j < firstList.size() ; j++ ) {
            visited[firstList.get(j).getLabel()] ++;
        }
        while(!myQueue.isEmpty()) {
            ArrayList<Vertex> myList = myQueue.remove();
            for(int i = 0 ; i< myList.size() ; i++) {
                if(visited[myList.get(i).getLabel()] == 0){
                    visited[myList.get(i).getLabel()] ++;
                    randomVertexCover.add(myList.get(i));
                }
            }
        }
        return randomVertexCover;
    }
}