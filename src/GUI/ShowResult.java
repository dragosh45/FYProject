package GUI;

import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.*;
/**
Frame provides the following purpose:
To show results for sorting algorithms and binary search
*/
class ShowResult extends JFrame {
    //creating JPanel instances
    JPanel upContent = new JPanel();
    JPanel downContent = new JPanel();
    //creating instances of text title JLabels
    JLabel upLabel = new JLabel("Running Results");
    JLabel downLabel = new JLabel("Running Time Results");
    //creating instances of text sub titles
    JLabel upLabel1 = new JLabel();
    JLabel downLabel1 = new JLabel();
    JLabel downLabel2 = new JLabel();
    //creating instance of JSplitPane
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(upContent), new JScrollPane(downContent));
    public ShowResult(String result, Long runningTime, String runningTimeExplain) {
        //setting up layout
        upContent.setLayout(new BoxLayout(upContent, BoxLayout.Y_AXIS));
        downContent.setLayout(new BoxLayout(downContent, BoxLayout.Y_AXIS));
        //setting up font
        Font font = upLabel.getFont();
        downLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        attributes.put(TextAttribute.SIZE, 20);
        upLabel.setFont(font.deriveFont(attributes));
        downLabel.setFont(font.deriveFont(attributes));
        //adding up components
        upContent.add(upLabel);
        upContent.add(upLabel1);
        downContent.add(downLabel);
        downContent.add(downLabel1);
        downContent.add(downLabel2);
        add(splitPane);
        //setting up text and getting results and time results
        upLabel1.setText("Results are: " + result);
        String stringRunningTime = Long.toString(runningTime);
        downLabel1.setText("In theory running time: " + runningTimeExplain);
        downLabel2.setText("Running time in nanoseconds is: " + stringRunningTime);
        //frame
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}