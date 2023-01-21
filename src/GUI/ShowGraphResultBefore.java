package GUI;

import javax.swing.*;
import java.awt.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
/**
Frame provides the following purpose:
To show the graph before the algorithm run and a description of the graph
*/
class ShowGraphResultBefore extends JFrame {
    //creating JPanel instances
    JPanel upContent = new JPanel();
    JPanel downContent = new JPanel();
    //constants for jgraphx layout
    private static final Dimension DEFAULT_SIZE = new Dimension(930,720);

    public ShowGraphResultBefore(ListenableGraph<String,DefaultEdge> beforeG,String description) {
        //setting up swing content layout
        upContent.setLayout(new BoxLayout(upContent, BoxLayout.Y_AXIS));
        downContent.setLayout(new BoxLayout(downContent, BoxLayout.Y_AXIS));
        //creating instance of JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(upContent), new JScrollPane(downContent));
        //adding g to the adapter
        //creating JGraphXAdapter
        JGraphXAdapter<String, DefaultEdge> jgxAdapterG = new JGraphXAdapter<>(beforeG);
        //creating and adding the adapter to a mx graph component
        mxGraphComponent componentG = new mxGraphComponent(jgxAdapterG);
        componentG.setConnectable(false);
        componentG.getGraph().setAllowDanglingEdges(false);
        //adding up components
        upContent.add(componentG);
        //adding down description
        JLabel downLabel3 = new JLabel("Description of instance: " + description);
        downContent.add(downLabel3);
        //adding split pane
        add(splitPane);
        //setting jgraphx layout
        mxFastOrganicLayout layoutG = new mxFastOrganicLayout(jgxAdapterG);
        layoutG.execute(jgxAdapterG.getDefaultParent());
        //frame
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
