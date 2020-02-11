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

    public void executeProcess(int currentTime) {
        int executionTime = 10 - this.processPriority;
        try {
            Thread.sleep(executionTime);
        } catch (InterruptedException ex) {
            System.out.println("Process interrupted, closing program...");
            System.exit(2);
        }
        // reduce remaining time
        this.processRemainingRuntime = this.processRemainingRuntime - executionTime;

        if (this.processRemainingRuntime <= 0) {

            endProcess();
        }
    }

    @Override
    public int compareTo(ProcessInfo process) {
        // compare processPriority
        return Integer.compare(this.processPriority, process.processPriority);
    }

    @Override
    public String toString() {
        return "Process Name: " + this.processName + "\tProcess Id: " + this.processId + "\tProcess Priority: " +
                this.processPriority + "\tProcess Remaining Runtime: " + this.processRemainingRuntime;
    }

    public String displayCompletedInfo() {
        return "";
    }

    public void endProcess() {

    }

}
