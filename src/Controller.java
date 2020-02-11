/*
written by: Adam Maser
CSC 420
Week 4 Project
Controller.java - Execution class for process-scheduler
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        // start program
        Controller program = new Controller();
        program.start();
    }

    private void start() {
        loadFile();
    }

    private void loadFile() {
        // create Scanner object
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("processes.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found, exiting program...");
            System.exit(2);
        }
        // best
        ArrayList<ProcessInfo> processes = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String[] info = fileReader.nextLine().split("\\|");
            processes.add(new ProcessInfo(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3])));
            fileReader.nextLine();
        }
    }
}
