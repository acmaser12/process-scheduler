/*
written by: Adam Maser
CSC420
Week 4 Project
MyHeap -- implements Heap class from section 23.9 of book

DERIVED FROM: Section 23.6 of course textbook
 */

import java.util.ArrayList;

public class Heap<ProcessInfo extends Comparable<ProcessInfo>> {

    private ArrayList<ProcessInfo> list = new ArrayList<>();

    Heap(ArrayList<ProcessInfo> processes) {
        for (ProcessInfo process : processes) {
            add(process);
        }
    }

    private void add(ProcessInfo process) {
        list.add(process);  // add to heap
        int currentIndex = list.size() - 1;  // get index of last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // swap if current process is greater than its parent
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0) {
                ProcessInfo temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else {
                break; // if else, tree is a heap
            }
            currentIndex = parentIndex;
        }
    }

    public ProcessInfo remove() {
        if (list.size() == 0) return null;

        ProcessInfo removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // find maximum of two children
            if (leftChildIndex >= list.size()) break;

            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // swap if the current node is less than the max
            if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                ProcessInfo temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else
                break;
        }

        return removedObject;
    }

    int getSize() {
        return list.size();
    }

    ArrayList<ProcessInfo> getList() { return this.list; }
}
