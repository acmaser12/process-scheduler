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
        // output required info
        System.out.println("Submitted by Adam Maser - masera1@csp.edu");
        System.out.println("I certify that this is my own work\n");

        // load data from file
        ArrayList<ProcessInfo> processes = loadFile();

        // create heap of ProcessInfo Objects
        // creating new heap also sorts the objects into heap (see Heap class)
        Heap<ProcessInfo> newHeap = new Heap<>(processes);

        // output heap by level
        printHeap(newHeap);

        // execute each process
        Heap<ProcessInfo> finishedProcesses = executeProcesses(newHeap);

        // loop through and print the results of each process
        System.out.println("\n");
        for (int i = 0; i < finishedProcesses.getSize(); i++) {
            System.out.println(finishedProcesses.getList().get(i).displayCompletedInfo());
        }

    }

    private ArrayList<ProcessInfo> loadFile() {
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
        }

        // return ArrayList of Processes
        return processes;
    }

    private void printHeap(Heap<ProcessInfo> heap) {
        int levelSize = 1;
        int location = 0;
        int currentLevel = 0;
        ArrayList<ProcessInfo> heapList = heap.getList();

        while (location < heap.getSize()) {
            System.out.println("Current Level: " + currentLevel);
            for (int i = 0; i < levelSize; i++) {
                if (location >= heap.getSize()) {
                    break;
                }
                System.out.println("\t" + heapList.get(location).toString());
                location++;
            }
            currentLevel++;
            levelSize *= 2;
        }
    }

    private Heap<ProcessInfo> executeProcesses(Heap<ProcessInfo> heap) {
        ArrayList<ProcessInfo> heapList = heap.getList();
        int originalSize = heap.getSize();
        ArrayList<ProcessInfo> completedList = new ArrayList<>();
        while (completedList.size() < originalSize) {
            for (int i = 0; i < heap.getSize(); i++) {
                boolean isCompleted = heapList.get(i).executeProcess((int)System.currentTimeMillis() % 10000);
                if (!isCompleted) {
                    completedList.add(heapList.get(i));
                    heapList.remove(i);
                }
            }
        }

        return new Heap<ProcessInfo>(completedList);
    }
}
