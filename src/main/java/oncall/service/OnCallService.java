package oncall.service;

import oncall.model.CalendarModel;
import oncall.model.WorkerModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OnCallService {
    CalendarModel calendarModel;
    WorkerModel weekdayWorkerModel;
    WorkerModel holidayWorkerModel;
    String prevWorker;

    public OnCallService(CalendarModel calendarModel, WorkerModel weekdayWorkerModel, WorkerModel holidayWorkerModel) {
        this.calendarModel = calendarModel;
        this.weekdayWorkerModel = weekdayWorkerModel;
        this.holidayWorkerModel = holidayWorkerModel;
        this.prevWorker = "";
    }

    private String callWorker(WorkerModel workerModel) {
        String currentWorker = workerModel.getCurrentWorker();
        if (currentWorker.equals(prevWorker)) {
            currentWorker = workerModel.runAwayWorker();
        }
        prevWorker = currentWorker;
        return currentWorker;
    }

    private String callCurrentWorker() {
        String currentCalendar = calendarModel.currentToString();
        String currentWorker;

        if (calendarModel.isCurrentHoliday()) {
            currentWorker = callWorker(holidayWorkerModel);
            return currentCalendar + " " + currentWorker;
        }
        currentWorker = callWorker(weekdayWorkerModel);
        return currentCalendar + " " + currentWorker;
    }

    public ArrayList<String> getCallWorkerList() {
        ArrayList<String> callWorkerList = new ArrayList<>();

        do {
            callWorkerList.add(callCurrentWorker());
        } while (calendarModel.nextDay());
        return callWorkerList;
    }
}
