/*
written by: Adam Maser
CSC420
Week 4 Project
ProcessInfo.java -- stores information for each ProcessInfo object
 */

public class ProcessInfo implements Comparable<ProcessInfo>{
    private String processName;
    private int processId;
    private int processPriority;
    private int processRemainingRuntime;
    private long processStartTime;
    private long processEndTime;
    private long processElapsedTime;

    public ProcessInfo(String processName, int processId, int processPriority, int processRemainingRuntime) {
        this.processName = processName;
        this.processId = processId;
        this.processPriority = processPriority;
        this.processRemainingRuntime = processRemainingRuntime;
    }

    public boolean executeProcess(int val) {
        return true;
    }

    @Override
    public int compareTo(ProcessInfo process) {
        return 1;
    }

    @Override
    public String toString() {
        return "";
    }

    public String displayCompletedInfo() {
        return "";
    }

    public void endProcess() {

    }
}
