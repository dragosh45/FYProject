package backend;

import graph.Vertex;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultUndirectedGraph;
import java.util.*;
/**
Class for backend purpose: Converts algorithm output to the GUI required format
*/
public class OutputConversion {
    /**
     * Converts Array to StringArray and returns the StringArray
     * @param intArray : int array to be converted
     * @return stringArray : String array to be returned
     */
    public String[] transformIntArrayToStringArray(int[] intArray) {
        String[] stringArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++)
            stringArray[i] = String.valueOf(intArray[i]);
        return stringArray;
    }
    /**
     * Converts StringArray to a String and returns the String
     * @param stringArray : String array to be converted
     * @return string : string to be returned
     */
    public String transformStringArrayToString(String[] stringArray) {
        String string = Arrays.toString(stringArray);
        return string;
    }
    /**
    Returns a string from an Array
    */
    public String sendString(int[] intArray) {
        return transformStringArrayToString(transformIntArrayToStringArray(intArray));
    }
    /**
     * Method to check if a vertex is covered
     * @param vertexCover the cover to check
     * @param label the label of a vertex to check
     * @return
     */
    public boolean checkIfCovered(ArrayList<Vertex> vertexCover, int label) {
        for(int p = 0; p< vertexCover.size() ; p++) {
            if (vertexCover.get(p).getLabel() == label) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method to mark the covered vertex in the graph
     * @param adjList
     * @param vertexCover
     * @return
     */
    public ListenableGraph<String, DefaultEdge> markCoveredVertexes(HashMap<Integer, ArrayList<Vertex>> adjList,ArrayList<Vertex> vertexCover) {
        ListenableGraph<String,DefaultEdge> G =new DefaultListenableGraph<>(new DefaultUndirectedGraph<>(DefaultEdge.class));
        for (Integer I : adjList.keySet()) {
            String valueX = null;
            String valueY = null;
            if(checkIfCovered(vertexCover, I.intValue())) {
                valueX = I.toString() + " CV";
            } else {
                valueX = I.toString();
            }
            for(int i = 1 ; i<adjList.get(I).size() ; i++) {
                int intValueY = adjList.get(I).get(i).getLabel();
                if(checkIfCovered(vertexCover,intValueY)) {
                    valueY = intValueY + " CV";
                } else {
                    valueY = Integer.toString(intValueY);
                }
                G.addVertex(valueX);
                G.addVertex(valueY);
                G.addEdge(valueX,valueY);
            }
        }
        return G;
    }
    /**
     Converting boolean to string
     Returning string result
     */
    public String convertResultBoolean(boolean b) {
        String result;
        result = Boolean.toString(b);
        return result;
    }
    /**
     Converting array to string
     Returning string result
     */
    public String convertResultArray(int[] array) {
        String result;
        OutputConversion arrayOutputKey = new OutputConversion();
        result = arrayOutputKey.sendString(array);
        return result;
    }
    public String transformToString(ArrayList<Vertex> vertexCover) {
        String stringVertexes = "";
        for(int i = 0 ; i<vertexCover.size() ; i++) {
            stringVertexes = stringVertexes +  vertexCover.get(i).getLabel() + " ";
        }
        return stringVertexes;
    }
}