package oncall.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WorkerModel {
    private ArrayList<String> workerList;
    private Queue<String> waitWorkers;
    private int workerIndex;

    public WorkerModel(List<String> workerList) {
        this.workerList = new ArrayList<>(workerList);
        this.waitWorkers = new LinkedList<>();
        this.workerIndex = 0;
    }

    public String getCurrentWorker() {
        String currentWorker = getWaitWorker();
        if (currentWorker != null) {
            return currentWorker;
        }
        currentWorker = workerList.get(workerIndex);
        this.workerIndex = (this.workerIndex + 1) % this.workerList.size();
        return currentWorker;
    }

    public boolean isWaitWorker() {
        return !waitWorkers.isEmpty();
    }

    public int getListSize() {
        return workerList.size();
    }

    private String getWaitWorker() {
        if (isWaitWorker()) {
            return waitWorkers.poll();
        }
        return null;
    }

    public String runAwayWorker() {
        int grepWorkerIndex = this.workerIndex - 1;
        if (grepWorkerIndex < 0) {
            grepWorkerIndex = this.workerList.size();
        }
        this.waitWorkers.add(workerList.get(grepWorkerIndex));
        return workerList.get(this.workerIndex++);
    }
}
