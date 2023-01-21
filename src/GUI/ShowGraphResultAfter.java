package GUI;

import javax.swing.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
/**
Frame provides the following purpose:
To show results for runned algorithms / running time in nanoseconds / running time explanations
*/
class ShowGraphResultAfter extends JFrame {
    //creating JPanel instances
    JPanel upContent = new JPanel();
    JPanel downContent = new JPanel();

    public ShowGraphResultAfter(ListenableGraph<String,DefaultEdge> afterG,Long runningTime,String vertexCover, int nrOfVertexes,int vertexCoverNumber) {
        //setting up swing content layout
        upContent.setLayout(new BoxLayout(upContent, BoxLayout.Y_AXIS));
        downContent.setLayout(new BoxLayout(downContent, BoxLayout.Y_AXIS));
        //creating instance of JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(upContent), new JScrollPane(downContent));
        //adding g to the adapter
        //creating JGraphXAdapter
        JGraphXAdapter<String, DefaultEdge> jgxAdapterG = new JGraphXAdapter<>(afterG);
        jgxAdapterG.setCellStyle("strokeColor=#CCCC00");
        jgxAdapterG.getStylesheet().getDefaultVertexStyle();
        //creating and adding the adapter to a mx graph component
        mxGraphComponent componentG = new mxGraphComponent(jgxAdapterG);
        componentG.setConnectable(false);
        componentG.getGraph().setAllowDanglingEdges(false);
        //adding up components
        upContent.add(componentG);
        //adding down components
        String stringRunningTime = Long.toString(runningTime);
        JLabel downLabel1 = new JLabel("Time this algorithm took to run in nanoseconds is: " + stringRunningTime);
        JLabel downLabel2 = new JLabel("Vertex cover set is: " + vertexCover);
        JLabel downLabel3 = new JLabel("Covered " + vertexCoverNumber + " vertexes out of " + nrOfVertexes);
        downContent.add(downLabel1);
        downContent.add(downLabel2);
        downContent.add(downLabel3);
        //adding split pane
        add(splitPane);
        //setting jgraphx layout
        mxFastOrganicLayout layoutG = new mxFastOrganicLayout(jgxAdapterG);
        layoutG.execute(jgxAdapterG.getDefaultParent());
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}