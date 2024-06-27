package oncall.controller;

import oncall.model.CalendarModel;
import oncall.model.KoDay;
import oncall.model.WorkerModel;
import oncall.view.OnCallInputView;
import oncall.view.OnCallOutputView;

import java.util.Arrays;
import java.util.List;

public class OncallController {
    final private OnCallInputView inputView;
    final private OnCallOutputView outputView;

    private CalendarModel calendarModel;
    private WorkerModel weekDayWorkerModel;
    private WorkerModel holidayWorkerModel;

    public OncallController(OnCallInputView inputView, OnCallOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void onCall() {
        inputData();
    }

    private void inputData() {
        List<String> workerList;

        inputFirstLine();
        workerList = inputWorkerList("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        weekDayWorkerModel = new WorkerModel(workerList);
        workerList = inputWorkerList("주말 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        holidayWorkerModel = new WorkerModel(workerList);

        outputView.printLine(calendarModel.currentToString());
        outputView.printLine(weekDayWorkerModel.getCurrentWorker() + " 포함 " + weekDayWorkerModel.getListSize() + "명");
        outputView.printLine(holidayWorkerModel.getCurrentWorker() + " 포함 " + holidayWorkerModel.getListSize() + "명");
    }

    private void inputFirstLine() {
        String line = inputView.getLine("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        try {
            int month = Integer.parseInt(line.split("[,|\\s]+")[0].trim());
            KoDay koDay = KoDay.valueOf(line.split("[,|\\s]+")[1].trim());

            if (month < 1 || month > 12) {
                throw new IllegalArgumentException();
            }
            calendarModel = new CalendarModel(month, koDay);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] Invalid Input.");
        }
    }

    private List<String> inputWorkerList(String prompt) {
        String line = inputView.getLine(prompt);
        List<String> workerList =  Arrays.stream(line.split("[,|\\s]+"))
                .map(String::trim)
                .toList();
        if (!(5 <= workerList.size() && workerList.size() <= 35)
                || workerList.size() != workerList.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] Invalid Input.");
        }

        for (String worker : workerList) {
            if (worker.length() > 5) {
                throw new IllegalArgumentException("[ERROR] Invalid Input.");
            }
        }
        return workerList;
    }
}
