package GUI;

import backend.*;
import algo.*;
import graph.Edge;
import graph.Vertex;
import graph.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
/**
Second frame of the GUI containing algorithm list and buttons
Provides the functionality to run selected algorithm with file input or keyboard input
*/
class AlgorithmList extends JFrame {
    //GUI components
    JList list;
    String[] listColorNames = {"InsertionSort", "SelectionSort", "BinarySearch","RandomVertexCover","GreedyVertexCover","2ApproximationVertexCover","HeuristicVertexCover"};
    Color[] listColorValues = {Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE, Color.BLACK,Color.BLUE};
    Container contentpane;
    int[] keyboardArrayResult;
    int[] fileArrayResult;
    public AlgorithmList() {
        //algorithms big O running time explained
        String insertionSortExplained = "Insertion sort has worst case O(n^2)";
        String binarySearchExplained = "Binary search has worst case O(logn)";
        String selectionSortExplained = "Selection sort has worst case O(n^2)";
        //pane settings
        contentpane = getContentPane();
        contentpane.setLayout(new BoxLayout(contentpane, BoxLayout.Y_AXIS));
        //list settings
        list = new JList(listColorNames);
        list.setSelectedIndex(0);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentpane.add(new JScrollPane(list));
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent se) {
                contentpane.setBackground(listColorValues[list.getSelectedIndex()]);
            }
        });
        //add open button
        JButton open = new JButton("Modify input.txt file or benchmarkLibrary.txt");
        contentpane.add(open);
        //set action event when button hit to open a file :
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                OpenFile openFile = new OpenFile();
            }
        });
        //add 'Run from File' button
        JButton fileInsert = new JButton("Run selected algorithm with input from file input.txt");
        contentpane.add(fileInsert);
        //action listener on 'Run From File'
        fileInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent be0) {
                ReadFromFile fileInput = new ReadFromFile();
                InputConversion input = new InputConversion();
                OutputConversion output = new OutputConversion();
                if (list.getSelectedIndex() == 0) {
                    InsertionSort insertionSort = new InsertionSort();
                    int[] arrayInput = input.transformArrayListToArray(fileInput.readAndSendArrayList());
                    long startTime = System.nanoTime();
                    fileArrayResult = insertionSort.insertionSort(arrayInput);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    new ShowResult(output.convertResultArray(fileArrayResult), runningTime, insertionSortExplained).setVisible(true);
                }
                if (list.getSelectedIndex() == 1) {
                    SelectionSort selectionSort = new SelectionSort();
                    int[] arrayInput = input.transformArrayListToArray(fileInput.readAndSendArrayList());
                    long startTime = System.nanoTime();
                    fileArrayResult = selectionSort.selectionSort(arrayInput);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    new ShowResult(output.convertResultArray(fileArrayResult), runningTime, selectionSortExplained).setVisible(true);
                }
                if (list.getSelectedIndex() == 2) {
                    BinarySearch binarySearch = new BinarySearch();
                    int[] arrayInput = input.transformArrayListToArray(fileInput.readAndSendArrayList());
                    long startTime = System.nanoTime();
                    Boolean b = binarySearch.binarySearch(arrayInput, arrayInput[0]);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    new ShowResult(output.convertResultBoolean(b), runningTime, binarySearchExplained).setVisible(true);
                }
                if(list.getSelectedIndex() == 3) {
                    ArrayList<Integer> arrayListFileInput = fileInput.readAndSendArrayList();
                    String stringFileInput = arrayListFileInput.toString();
                    String description = "the graph has the following incidence list: " + stringFileInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(fileInput.readAndSendArrayList());
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    RandomVertexCover randomVertexCover = new RandomVertexCover();
                    long startTime = System.nanoTime();
                    ArrayList<Vertex> randomVertexCoverSet = randomVertexCover.randomVertexCover(maxValueVertex,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    ArrayList<Vertex> randomVertexCoverSetArray = randomVertexCover.randomVertexCover(maxValueVertex,graph);
                    String randomVertexCoverSetString = output.transformToString(randomVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,randomVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, randomVertexCoverSetString, nrOfVertexes,randomVertexCoverSetArray.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 4) {
                    ArrayList<Integer> arrayListFileInput = fileInput.readAndSendArrayList();
                    String stringFileInput = arrayListFileInput.toString();
                    String description = "the graph has the following incidence list: " + stringFileInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(arrayListFileInput);
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    GreedyVertexCover greedyVertexCover = new GreedyVertexCover();
                    long startTime = System.nanoTime();
                    ArrayList<Vertex> greedyVertexCoverSet = greedyVertexCover.greedyVertexCover(maxValueVertex,graph);
                    String greedyVertexCoverSetString = output.transformToString(greedyVertexCoverSet);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,greedyVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, greedyVertexCoverSetString, nrOfVertexes,greedyVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 5) {
                    ArrayList<Integer> arrayListFileInput = fileInput.readAndSendArrayList();
                    String stringFileInput = arrayListFileInput.toString();
                    String description = "the graph has the following incidence list: " + stringFileInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(arrayListFileInput);
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    ArrayList<Edge> edgeList =  graph.getEdgeList(hashMapInput);
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    MaximalGraphMatching maxMatchInstance = new MaximalGraphMatching();
                    long startTime = System.nanoTime();
                    ArrayList<Edge> maximalMatching = maxMatchInstance.maximalGraphMatching(maxValueVertex,edgeList,graph);
                    TwoApproximationVertexCover approxVertexCoverInstance = new TwoApproximationVertexCover();
                    ArrayList<Vertex> twoApproxMinVertexCover = approxVertexCoverInstance.twoApproximationVertexCover(maximalMatching,maxValueVertex);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String twoApproxVertexCoverSetString = output.transformToString(twoApproxMinVertexCover);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,twoApproxMinVertexCover);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, twoApproxVertexCoverSetString, nrOfVertexes,twoApproxMinVertexCover.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 6) {
                    ArrayList<Integer> arrayListFileInput = fileInput.readAndSendArrayList();
                    String stringFileInput = arrayListFileInput.toString();
                    String description = "the graph has the following incidence list: " + stringFileInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(arrayListFileInput);
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    ArrayList<Edge> edgeList =  graph.getEdgeList(hashMapInput);
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    ArrayList<Vertex> vertexList = graph.getVertexSet(hashMapInput);
                    MaximalGraphMatching maxMatchInstance = new MaximalGraphMatching();
                    long startTime = System.nanoTime();
                    ArrayList<Edge> maximalMatching = maxMatchInstance.maximalGraphMatching(maxValueVertex,edgeList,graph);
                    TwoApproximationVertexCover approxVertexCoverInstance = new TwoApproximationVertexCover();
                    ArrayList<Vertex> twoApproxMinVertexCoverXList = approxVertexCoverInstance.twoApproximationVertexCoverOneVertex(maximalMatching,maxValueVertex);
                    ArrayList<Vertex> twoApproxMinVertexCoverXYList = approxVertexCoverInstance.twoApproximationVertexCover(maximalMatching,maxValueVertex);
                    ArrayList<Vertex> twoApproxMinVertexCoverYList = approxVertexCoverInstance.twoApproximationVertexCoverSecondVertex(maximalMatching,maxValueVertex);
                    HeuristicVertexCover heuristicVertexCoverInstance = new HeuristicVertexCover();
                    ArrayList<Vertex> heuristicVertexCoverSet = heuristicVertexCoverInstance.heuristicVertexCover(vertexList,twoApproxMinVertexCoverXList,twoApproxMinVertexCoverYList,twoApproxMinVertexCoverXYList,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String heuristicVertexCoverSetString = output.transformToString(heuristicVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,heuristicVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, heuristicVertexCoverSetString, nrOfVertexes,heuristicVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
            }
        });
        JButton benchMarkInsert = new JButton("Run selected algorithm with random benchmark instance");
        contentpane.add(benchMarkInsert);
        //action listener on 'Run From benchmarkFile'
        benchMarkInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent be0) {
                ReadFromFile fileInput = new ReadFromFile();
                InputConversion input = new InputConversion();
                OutputConversion output = new OutputConversion();
                if(list.getSelectedIndex() == 3) {
                    String stringInput = fileInput.readFromFileAndSendRandomLine();
                    String description = fileInput.getDescription(stringInput);
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformBenchmarkLineInputToArrayList(stringInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    RandomVertexCover randomVertexCover = new RandomVertexCover();
                    long startTime = System.nanoTime();
                    ArrayList<Vertex> randomVertexCoverSet = randomVertexCover.randomVertexCover(maxValueVertex,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String randomVertexCoverSetString = output.transformToString(randomVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,randomVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, randomVertexCoverSetString, nrOfVertexes,randomVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 4) {
                    String stringInput = fileInput.readFromFileAndSendRandomLine();
                    String description = fileInput.getDescription(stringInput);
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformBenchmarkLineInputToArrayList(stringInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    GreedyVertexCover greedyVertexCover = new GreedyVertexCover();
                    long startTime = System.nanoTime();
                    ArrayList<Vertex> greedyVertexCoverSet = greedyVertexCover.greedyVertexCover(maxValueVertex,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String greedyVertexCoverSetString = output.transformToString(greedyVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,greedyVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, greedyVertexCoverSetString, nrOfVertexes,greedyVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 5) {
                    String stringInput = fileInput.readFromFileAndSendRandomLine();
                    String description = fileInput.getDescription(stringInput);
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformBenchmarkLineInputToArrayList(stringInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    ArrayList<Edge> edgeList =  graph.getEdgeList(hashMapInput);
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    MaximalGraphMatching maxMatchInstance = new MaximalGraphMatching();
                    long startTime = System.nanoTime();
                    ArrayList<Edge> maximalMatching = maxMatchInstance.maximalGraphMatching(maxValueVertex,edgeList,graph);
                    TwoApproximationVertexCover approxVertexCoverInstance = new TwoApproximationVertexCover();
                    ArrayList<Vertex> twoApproxMinVertexCover = approxVertexCoverInstance.twoApproximationVertexCover(maximalMatching,maxValueVertex);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String twoApproxVertexCoverSetString = output.transformToString(twoApproxMinVertexCover);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,twoApproxMinVertexCover);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, twoApproxVertexCoverSetString, nrOfVertexes,twoApproxMinVertexCover.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 6) {
                    String stringInput = fileInput.readFromFileAndSendRandomLine();
                    String description = fileInput.getDescription(stringInput);
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformBenchmarkLineInputToArrayList(stringInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    ArrayList<Edge> edgeList =  graph.getEdgeList(hashMapInput);
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    ArrayList<Vertex> vertexList = graph.getVertexSet(hashMapInput);
                    MaximalGraphMatching maxMatchInstance = new MaximalGraphMatching();
                    long startTime = System.nanoTime();
                    ArrayList<Edge> maximalMatching = maxMatchInstance.maximalGraphMatching(maxValueVertex,edgeList,graph);
                    TwoApproximationVertexCover approxVertexCoverInstance = new TwoApproximationVertexCover();
                    ArrayList<Vertex> twoApproxMinVertexCoverXList = approxVertexCoverInstance.twoApproximationVertexCoverOneVertex(maximalMatching,maxValueVertex);
                    ArrayList<Vertex> twoApproxMinVertexCoverXYList = approxVertexCoverInstance.twoApproximationVertexCover(maximalMatching,maxValueVertex);
                    ArrayList<Vertex> twoApproxMinVertexCoverYList = approxVertexCoverInstance.twoApproximationVertexCoverSecondVertex(maximalMatching,maxValueVertex);
                    HeuristicVertexCover heuristicVertexCoverInstance = new HeuristicVertexCover();
                    ArrayList<Vertex> heuristicVertexCoverSet = heuristicVertexCoverInstance.heuristicVertexCover(vertexList,twoApproxMinVertexCoverXList,twoApproxMinVertexCoverYList,twoApproxMinVertexCoverXYList,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String heuristicVertexCoverString = output.transformToString(heuristicVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,heuristicVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, heuristicVertexCoverString, nrOfVertexes,heuristicVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
            }
        });
        //text field settings
        JTextField textField = new JTextField("5 2 3 1");
        textField.setVisible(true);
        contentpane.add(textField);
        //add 'Run from keyboard' button
        JButton keyboardInsert = new JButton("Run selected algorithm with the input inserted above ");
        contentpane.add(keyboardInsert);
        //action listener on 'Run from keyboard' button
        keyboardInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent be1) {
                InputConversion input = new InputConversion();
                OutputConversion output = new OutputConversion();
                if (list.getSelectedIndex() == 0) {
                    InsertionSort insertionSort = new InsertionSort();
                    int[] arrayInput = input.sendArrayInput(textField.getText());
                    long startTime = System.nanoTime();
                    keyboardArrayResult = insertionSort.insertionSort(arrayInput);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    new ShowResult(output.convertResultArray(keyboardArrayResult), runningTime, insertionSortExplained).setVisible(true);
                }
                if (list.getSelectedIndex() == 1) {
                    SelectionSort selectionSort = new SelectionSort();
                    int[] arrayInput = input.sendArrayInput(textField.getText());
                    long startTime = System.nanoTime();
                    keyboardArrayResult = selectionSort.selectionSort(arrayInput);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    new ShowResult(output.convertResultArray(keyboardArrayResult), runningTime, selectionSortExplained).setVisible(true);
                }
                if (list.getSelectedIndex() == 2) {
                    BinarySearch binarySearch = new BinarySearch();
                    int[] arrayInput = input.sendArrayInput(textField.getText());
                    long startTime = System.nanoTime();
                    Boolean b = binarySearch.binarySearch(arrayInput, arrayInput[0]);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    new ShowResult(output.convertResultBoolean(b), runningTime, binarySearchExplained).setVisible(true);
                }
                if(list.getSelectedIndex() == 3) {
                    String textFieldInput = textField.getText();
                    String description = "the graph has the following incidence list: " + textFieldInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformInputToArrayList(textFieldInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX = input.transformHashMapToJgraphx(inputGraph);
                    RandomVertexCover randomVertexCover = new RandomVertexCover();
                    long startTime = System.nanoTime();
                    ArrayList<Vertex> randomVertexCoverSet = randomVertexCover.randomVertexCover(maxValueVertex,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String randomVertexCoverSetString = output.transformToString(randomVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph, randomVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, randomVertexCoverSetString, nrOfVertexes,randomVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 4) {
                    String textFieldInput = textField.getText();
                    String description = "the graph has the following incidence list: " + textFieldInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformInputToArrayList(textFieldInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int maxValueVertex = graph.getMaxNumberedVertex(hashMapInput);
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    GreedyVertexCover greedyVertexCover = new GreedyVertexCover();
                    long startTime = System.nanoTime();
                    ArrayList<Vertex> greedyVertexCoverSet = greedyVertexCover.greedyVertexCover(maxValueVertex,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String greedyVertexCoverSetString = output.transformToString(greedyVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,greedyVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, greedyVertexCoverSetString, nrOfVertexes,greedyVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 5) {
                    String textFieldInput = textField.getText();
                    String description = "the graph has the following incidence list: " + textFieldInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformInputToArrayList(textFieldInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    ArrayList<Edge> edgeList =  graph.getEdgeList(hashMapInput);
                    int maxValueVertex = input.getMaxNumberedVertex(input.transformInputToArrayList(textField.getText()));
                    MaximalGraphMatching maxMatchInstance = new MaximalGraphMatching();
                    long startTime = System.nanoTime();
                    ArrayList<Edge> maximalMatching = maxMatchInstance.maximalGraphMatching(maxValueVertex,edgeList,graph);
                    TwoApproximationVertexCover approxVertexCoverInstance = new TwoApproximationVertexCover();
                    ArrayList<Vertex> twoApproxMinVertexCover = approxVertexCoverInstance.twoApproximationVertexCover(maximalMatching,maxValueVertex);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String twoApproxMinVertexCoverString = output.transformToString(twoApproxMinVertexCover);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,twoApproxMinVertexCover);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, twoApproxMinVertexCoverString, nrOfVertexes,twoApproxMinVertexCover.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
                if(list.getSelectedIndex() == 6) {
                    String textFieldInput = textField.getText();
                    String description = "the graph has the following incidence list: " + textFieldInput;
                    HashMap<Integer, ArrayList<Vertex>> hashMapInput = input.transformArrayToHashMap(input.transformInputToArrayList(textFieldInput));
                    Graph graph = new Graph(hashMapInput);
                    HashMap<Integer, ArrayList<Vertex>> inputGraph = graph.getAdjList();
                    int nrOfVertexes = graph.getVertexesNo();
                    ListenableGraph<String, DefaultEdge> inputJGraphX =  input.transformHashMapToJgraphx(inputGraph);
                    ArrayList<Edge> edgeList =  graph.getEdgeList(hashMapInput);
                    ArrayList<Vertex> vertexList = graph.getVertexSet(hashMapInput);
                    int maxValueVertex = input.getMaxNumberedVertex(input.transformInputToArrayList(textField.getText()));
                    MaximalGraphMatching maxMatchInstance = new MaximalGraphMatching();
                    long startTime = System.nanoTime();
                    ArrayList<Edge> maximalMatching = maxMatchInstance.maximalGraphMatching(maxValueVertex,edgeList,graph);
                    TwoApproximationVertexCover approxVertexCoverInstance = new TwoApproximationVertexCover();
                    ArrayList<Vertex> twoApproxMinVertexCoverXList = approxVertexCoverInstance.twoApproximationVertexCoverOneVertex(maximalMatching,maxValueVertex);
                    ArrayList<Vertex> twoApproxMinVertexCoverXYList = approxVertexCoverInstance.twoApproximationVertexCover(maximalMatching,maxValueVertex);
                    ArrayList<Vertex> twoApproxMinVertexCoverYList = approxVertexCoverInstance.twoApproximationVertexCoverSecondVertex(maximalMatching,maxValueVertex);
                    HeuristicVertexCover heuristicVertexCoverInstance = new HeuristicVertexCover();
                    ArrayList<Vertex> heuristicVertexCoverSet = heuristicVertexCoverInstance.heuristicVertexCover(vertexList,twoApproxMinVertexCoverXList,twoApproxMinVertexCoverYList,twoApproxMinVertexCoverXYList,graph);
                    long endTime = System.nanoTime();
                    long runningTime = endTime - startTime;
                    String heuristicVertexCoverSetString = output.transformToString(heuristicVertexCoverSet);
                    ListenableGraph<String, DefaultEdge> outputJGraphX = output.markCoveredVertexes(inputGraph,heuristicVertexCoverSet);
                    new ShowGraphResultAfter(outputJGraphX, runningTime, heuristicVertexCoverSetString, nrOfVertexes,heuristicVertexCoverSet.size()).setVisible(true);
                    new ShowGraphResultBefore(inputJGraphX,description).setVisible(true);
                }
            }
        });
        setSize(440, 350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}