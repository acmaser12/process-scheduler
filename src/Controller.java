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
        // create ArrayList for processes
        ArrayList<ProcessInfo> processes = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String[] info = fileReader.nextLine().split("\\|");
            processes.add(new ProcessInfo(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3])));
        }

        // return ArrayList of Processes
        return processes;
    }

    private void printHeap(Heap<ProcessInfo> heap) {
        int levelSize = 1;  // number of elements in level
        int location = 0;  // location in heapList
        int currentLevel = 0;  // current level of heap
        ArrayList<ProcessInfo> heapList = heap.getList();  // get list from heap (which is sorted as a tree already)

        // loop through list and output each process in each level
        while (location < heap.getSize()) {
            System.out.println("Current Level: " + currentLevel);
            for (int i = 0; i < levelSize; i++) {
                // if the location is bigger than heap, break loop
                if (location >= heap.getSize()) {
                    break;
                }
                System.out.println("\t" + heapList.get(location).toString());
                location++;
            }
            // increment currentLevel and increase level (each subsequent level of a tree is 2x bigger than prev)
            currentLevel++;
            levelSize *= 2;
        }
    }

    private Heap<ProcessInfo> executeProcesses(Heap<ProcessInfo> heap) {
        // get list from heap
        ArrayList<ProcessInfo> heapList = heap.getList();
        // get size of heap
        int originalSize = heap.getSize();
        // create container for list of finished processes
        ArrayList<ProcessInfo> completedList = new ArrayList<>();
        // loop through processes until all are complete
        while (completedList.size() < originalSize) {
            for (int i = 0; i < heap.getSize(); i++) {
                boolean isCompleted = heapList.get(i).executeProcess((int)System.currentTimeMillis() % 10000);
                // if completed, add to completedList and remove from heapList
                if (!isCompleted) {
                    completedList.add(heapList.get(i));
                    heapList.remove(i);
                }
            }
        }
        return new Heap<>(completedList);
    }
}
