package backend;
import main.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
Class for backend purpose: reading GUI input from a file
*/
public class ReadFromFile {
    /**
     * Gets the current directory of the jar file
     * @return directory: the String which contains the path
     */
    public String getDirectory() {
        Main mainInstance = new Main();
        String directory = mainInstance.sendArgument();
        return directory;
    }
    /**
     Reads from the input.txt and returns a graph adjacency lists
     */
    public ArrayList<Integer> readAndSendArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        try {
            Scanner scanner = new Scanner(new File(getDirectory() + "/input.txt"));
            while (scanner.hasNextInt()) {
                arrayList.add(scanner.nextInt());
            }
            return arrayList;
        } catch(FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        }
        return arrayList;
    }
    /**
     * Reads from the benchmarkLibrary.txt file and returns a random
     * graph adjacency lists
     * @return randomLine
     */
    public String readFromFileAndSendRandomLine() {
        ArrayList<String> linesList = new ArrayList<String>();
        String randomLine = null;
        try {
            Scanner scanner = new Scanner(new File(getDirectory() + "/benchmarkLibrary.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                linesList.add(line);
            }
        }catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        }
        for (int i=0;i<linesList.size() ;i++ ) {
            if(linesList.get(i).charAt(0) == '#') {
                linesList.remove(i);
            }
        }
        Random r = new Random();
        int low = 1;
        int high = linesList.size();
        int result = r.nextInt(high-low);
        for (int i=-1;i<linesList.size() ;i++ ) {
            if(i == result) {
                randomLine = linesList.get(i-1);
            }
        }
        return randomLine;
    }
    /**
     * Returns the description from the benchmarkLibrary.txt according to the incidence list
     * @param lineSent incidence list
     * @return before - the description
     */
    public String getDescription(String lineSent){
        ArrayList<String> linesList = new ArrayList<String>();
        String before = null;
        try {
            Scanner scanner = new Scanner(new File(getDirectory() + "/benchmarkLibrary.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                linesList.add(line);
            }
        }catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        }
        for (int i=0;i<linesList.size() ;i++ ) {
            if(linesList.get(i).equals(lineSent)) {
                before = linesList.get(i-1);
            }
        }
        return before;
    }
}