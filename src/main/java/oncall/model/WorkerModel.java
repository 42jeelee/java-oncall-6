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
        return workerList.get(workerIndex);
    }

    public boolean isWaitWorker() {
        return !waitWorkers.isEmpty();
    }

    public int getListSize() {
        return workerList.size();
    }

    public String getWaitWorker() {
        if (isWaitWorker()) {
            return waitWorkers.poll();
        }
        return null;
    }

    public void callWorker() {
        this.workerIndex = (this.workerIndex + 1) % this.workerList.size();
    }

    public void waitWorker() {
        String currentWorker = getCurrentWorker();
        this.waitWorkers.add(currentWorker);
        callWorker();;
    }
}
