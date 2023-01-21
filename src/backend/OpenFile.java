package backend;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
Frame providing the function to open, modify and save file input or to inspect an algorithm
*/
public class OpenFile extends JFrame {
    Container contentpane;
    /**
     * Gets the current directory of the jar file
     * @return directory: the String which contains the path
     */
    public String getDirectory() {
        Main mainInstance = new Main();
        String directory = mainInstance.sendArgument();
        return directory;
    }
    public OpenFile(){
        contentpane = getContentPane();
        contentpane.setLayout(new BorderLayout());
        //FileChooser setting
        JFileChooser chooser = new JFileChooser(getDirectory());
        //JEditorPane settings
        final JEditorPane document = new JEditorPane();
        contentpane.add(new JScrollPane(document), BorderLayout.CENTER);
        JButton open = new JButton("Open");
        JButton save = new JButton("Save");
        open.addActionListener(ae -> {
            int result = chooser.showOpenDialog(contentpane);
            if (result==JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    document.setPage(file.toURI().toURL());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //action listener that saves input.txt
        save.addActionListener(aee -> {
            try {
                FileWriter out = new FileWriter(new File(getDirectory() + "/input.txt"));
                out.write(document.getText());
                out.close();
            } catch(Exception ee) {
                ee.printStackTrace();
            }
        });
        contentpane.add(open,BorderLayout.NORTH);
        contentpane.add(save,BorderLayout.SOUTH);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}