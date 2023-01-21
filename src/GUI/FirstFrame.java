package GUI;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;

/**
First frame of the GUI for instructions (HowTo) purpose
*/
public class FirstFrame extends JFrame {
    //Frame Constructor
    public FirstFrame() {
        //Creating main content panel
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        //Creating second content panel
        JPanel content2 = new JPanel();
        content2.setLayout(new BoxLayout(content2, BoxLayout.Y_AXIS));
        content2.setBorder(BorderFactory.createLineBorder(Color.red));
        content2.setOpaque(false);
        //Creating third content panel
        JPanel content3 = new JPanel();
        content3.setLayout(new BoxLayout(content3, BoxLayout.Y_AXIS));
        //Creating 4th content panel
        JPanel content4 = new JPanel();
        content4.setLayout(new BoxLayout(content4, BoxLayout.Y_AXIS));
        content4.setBorder(BorderFactory.createLineBorder(Color.blue));
        //Creating welcomeLabel
        JLabel welcomeLabel = new JLabel("Welcome, this is an instruction window, please read the following:");
        Font font = welcomeLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        attributes.put(TextAttribute.SIZE, 20);
        welcomeLabel.setFont(font.deriveFont(attributes));
        //Creating HowTo labels
        JLabel howtoLabel0 = new JLabel("Next window contains a list of algorithms (Click Show Algorithm List)", JLabel.CENTER);
        JLabel howtoLabel1 = new JLabel("Click on an algorithm name from the list", JLabel.CENTER);
        JLabel howtoLabel2 = new JLabel(" ", JLabel.CENTER);
        JLabel howtoLabel3 = new JLabel("1)File Input: ", JLabel.CENTER);
        JLabel howtoLabel4 = new JLabel("A step-To insert input from a file click: 'Modify input.txt file or benchmarkLibrary.txt'", JLabel.CENTER);
        JLabel howtoLabel11 = new JLabel("A step-To modify benchmark library file click: 'Modify input.txt file or benchmarkLibrary.txt'", JLabel.CENTER);
        JLabel howtoLabel5 = new JLabel("B step-The next window lets you 'Open' input.txt file, modify and 'Save'", JLabel.CENTER);
        JLabel howtoLabel6 = new JLabel("C step-To run selected algorithm click 'Run selected algorithm with input from input.txt'", JLabel.CENTER);
        JLabel howtoLabel12 = new JLabel("C step-To run a random instance from the benchmark library click 'Run selected algorithm with random benchmark instance'", JLabel.CENTER);
        JLabel howtoLabel7 = new JLabel(" ", JLabel.CENTER);
        JLabel howtoLabel8 = new JLabel("2)Keyboard Input: ", JLabel.CENTER);
        JLabel howtoLabel9 = new JLabel("A step-To insert input from keyboard: Modify 5 2 3 1 box keeping the same format", JLabel.CENTER);
        JLabel howtoLabel10 = new JLabel("B step-To run selected algorithm click 'Run selected algorithm with the input inserted above'", JLabel.CENTER);
        //Creating HowTo input format label
        JLabel formatWelcomeLabel = new JLabel("The next instructions tell what kind of input format to use for each algorithm:", JLabel.CENTER);
        Font font2 = formatWelcomeLabel.getFont();
        Map attributes2 = font2.getAttributes();
        attributes2.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        attributes2.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        attributes2.put(TextAttribute.SIZE, 15);
        formatWelcomeLabel.setFont(font2.deriveFont(attributes2));
        JLabel formatLabel0 = new JLabel("*Keyboard and File input format for sorting algorithms: Numbers separated by space, example: '2 3 1 7'", JLabel.CENTER);
        JLabel formatLabel5 = new JLabel("*Keyboard and File input format for binary search algorithm: Numbers separated by space, first number representing the number to search for, example: '1 2 3 1 7'", JLabel.CENTER);
        JLabel formatLabel6 = new JLabel("*Keyboard and File input format for graph algorithms: Numbers separated by space representing incidence lists,", JLabel.CENTER);
        JLabel formatLabel7 = new JLabel("with a 0 between each list and  the first number after 0 the number of the vertex example: '1 3 2 0 2 1 0 3 1 4 0 4 3 0' ", JLabel.CENTER);
        JLabel formatLabel8 = new JLabel("*Benchmark library file input has a first line beginning with # that contains the instance description, ", JLabel.CENTER);
        JLabel formatLabel9 = new JLabel("and the second line according to Keyboard and File input for graph algorithms explained above", JLabel.CENTER);
        JLabel formatLabel10 = new JLabel("*Benchmark library file input does not work for binary search algorithm or sorting algorithm ", JLabel.CENTER);
        //Creating Show Algorithms List Button
        JButton b = new JButton("Show Algorithm List");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AlgorithmList list = new AlgorithmList();
                list.setVisible(true);
                list.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        //Creating Exit Button
        JButton e = new JButton("Exit");
        e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //Adding labels to panel
        content.add(welcomeLabel);
        content2.add(howtoLabel0);
        content2.add(howtoLabel1);
        content2.add(howtoLabel2);
        content2.add(howtoLabel3);
        content2.add(howtoLabel4);
        content2.add(howtoLabel11);
        content2.add(howtoLabel5);
        content2.add(howtoLabel6);
        content2.add(howtoLabel12);
        content2.add(howtoLabel7);
        content2.add(howtoLabel8);
        content2.add(howtoLabel9);
        content2.add(howtoLabel10);
        content4.add(formatLabel0);
        content4.add(formatLabel5);
        content4.add(formatLabel6);
        content4.add(formatLabel7);
        content4.add(formatLabel8);
        content4.add(formatLabel9);
        content4.add(formatLabel10);
        content3.add(b);
        content3.add(e);
        //Adding content panel to frame
        add(content);
        add(content2);
        add(formatWelcomeLabel);
        add(content4);
        add(content3);
        ((JPanel) getContentPane()).setOpaque(false);
        //Setting frame layout
        setLayout(new FlowLayout());
    }
    //Method that constructs instance of Frame
    public static void createAndShowFirstFrame() {
        //Create and set up the window
        JFrame firstFrame = new FirstFrame();
        firstFrame.setTitle("Practical solutions for intractable problems");
        firstFrame.setSize(1200, 450);
        firstFrame.setVisible(true);
        firstFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
