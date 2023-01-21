package backend;

import graph.Vertex;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultUndirectedGraph;
import java.util.*;
/**
Class for backend purpose: Converts  input to the algorithms required format
*/
public class InputConversion {
    /**
     *Converts a String to an ArrayList<Integer> and returns
     *Exception: Catches exception if numbers are not in a correct format
     * @param input : String to be transformed
     * @return arrayList : transformed ArrayList<Integer>
     */
    public ArrayList<Integer> transformInputToArrayList(String input) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (String val : input.split(" ")) {
            try {
                int result = Integer.parseInt(val);
                arrayList.add(result);
            } catch (NumberFormatException exx) {
                System.out.println("Numbers must be separated by space, example: 1 2 0 2 1 3 0 3 2");
                exx.printStackTrace();
            }
        }
        return arrayList;
    }
    /**
     * Converts a String to an ArrayList<Integer> and returns
     * Exception: Catches exception if numbers are not in a correct format
     * @param input : String to be transformed
     * @return arrayList : transformed ArrayList<Integer>
     */
    public ArrayList<Integer> transformBenchmarkLineInputToArrayList(String input) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (String val : input.split(" ")) {
            try {
                int result = Integer.parseInt(val);
                arrayList.add(result);
            } catch (NumberFormatException exx) {
                System.out.println("Numbers must be separated by space, example: 1 2 0 2 1 3 0 3 2");
                exx.printStackTrace();
            }
        }
        return arrayList;
    }
    /**
     * Converts an ArrayList<Integer> to int[] array and returns
     * @param arrayList : ArrayList<Integer> to be transformed
     * @return array : transformed array
     */
    public int[] transformArrayListToArray(ArrayList<Integer> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            array[i] = arrayList.get(i);
        return array;
    }
    /**
     * Converts an ArrayList<Integer> to HashMap<Integer,ArrayList<Vertex>
     * @param inputArrayList : ArrayList<Integer> to be transformed
     * @return hashMap : transformed HashMap
     */
    public HashMap<Integer,ArrayList<Vertex>> transformArrayToHashMap(ArrayList<Integer> inputArrayList) {
        int[] array = transformArrayListToArray(inputArrayList);
        HashMap<Integer,ArrayList<Vertex>> hashMap = new HashMap<Integer,ArrayList<Vertex>>();
        ArrayList<Vertex> arrayList= new ArrayList();
        Vertex v;
        Iterator<HashMap.Entry<Integer, ArrayList<Vertex>>> I= hashMap.entrySet().iterator();
        int i = 0,k = 1;
        while(i < array.length) {
            if (array[i] != 0) {
                v = new Vertex(array[i]);
                arrayList.add(v);
            } else {
                hashMap.put(k,arrayList);
                k++;
                arrayList = new ArrayList<>();
            }
            i++;
        }
        return hashMap;
    }
    public int getMaxNumberedVertex(ArrayList<Integer> inputArrayList) {
        int maxNumberVertex = 0;
        int value2 = 0, value1 = 0;
        HashMap<Integer,ArrayList<Vertex>> adjList = transformArrayToHashMap(inputArrayList);
        for (Integer I : adjList.keySet()) {
            value1 = I.intValue();
            if(value1 > maxNumberVertex) {
                maxNumberVertex = value1;
            }
            for (int i = 1; i < adjList.get(I).size(); i++) {
                value2 = adjList.get(I).get(i).getLabel();
                if(value2 > maxNumberVertex) {
                    maxNumberVertex = value2;
                }
            }
        }
        return maxNumberVertex;
    }
    /**
     * Converts String to array by
     * @param input : String to be converted
     */
    public int[] sendArrayInput(String input) {
        return transformArrayListToArray(transformInputToArrayList(input));
    }
    /**
     * Converts HashMap<Integer,ArrayList<Vertex>> to Jgraphx form
     * @param adjList
     * @return G : converted Jgraphx form ListenableGraph<String,DefaultEdge>
     */
    public ListenableGraph<String, DefaultEdge> transformHashMapToJgraphx(HashMap<Integer, ArrayList<Vertex>> adjList) {
        ListenableGraph<String,DefaultEdge> G =new DefaultListenableGraph<>(new DefaultUndirectedGraph<>(DefaultEdge.class));
        for (Integer I : adjList.keySet()) {
            String valueX = I.toString();
            G.addVertex(valueX);
            for(int i = 1 ; i<adjList.get(I).size() ; i++) {
                int intValueY = adjList.get(I).get(i).getLabel();
                String valueY = Integer.toString(intValueY);
                G.addVertex(valueY);
                G.addEdge(valueX,valueY);
            }
        }
        return G;
    }
}