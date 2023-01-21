package main;

import GUI.FirstFrame;
import backend.ReadFromFile;

/**
main.Main class to run GUI
*/
public class Main {
    private static String argument;

    public static void main(String[] args) {
        //argument = args[0];
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FirstFrame.createAndShowFirstFrame();
            }
        });
    }
    public String sendArgument(){
      return argument;
    }
}