package oncall.controller;

import oncall.model.CalendarModel;
import oncall.model.KoDay;
import oncall.model.WorkerModel;
import oncall.service.OnCallService;
import oncall.service.ValidService;
import oncall.view.OnCallInputView;
import oncall.view.OnCallOutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OncallController {
    final private OnCallInputView inputView;
    final private OnCallOutputView outputView;

    private CalendarModel calendarModel;
    private WorkerModel weekDayWorkerModel;
    private WorkerModel holidayWorkerModel;

    private OnCallService onCallService;

    public OncallController(OnCallInputView inputView, OnCallOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void onCall() {
        inputData();

        ArrayList<String> callWorkerList = onCallService.getCallWorkerList();
        outputView.printList(callWorkerList);
    }

    private void inputData() {
        inputFirstLine();
        do {
            try {
                inputWeekdayWorkerList("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
                inputHolidayWorkerList("주말 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

                if (weekDayWorkerModel.getListSize() != holidayWorkerModel.getListSize()) {
                    throw new IllegalArgumentException("[ERROR] Invalid Input.");
                }
                break;
            } catch (IllegalArgumentException e) {
                outputView.printLine(e.getMessage());
            }
        } while (true);
        onCallService = new OnCallService(calendarModel, weekDayWorkerModel, holidayWorkerModel);
    }

    private void inputFirstLine() {
        do {
            String line = inputView.getLine("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            try {
                ValidService.isValidFirstLine(line);
                int month = Integer.parseInt(line.split("[,|\\s]+")[0].trim());
                KoDay koDay = KoDay.valueOf(line.split("[,|\\s]+")[1].trim());

                if (month < 1 || month > 12) {
                    throw new IllegalArgumentException("[ERROR] Invalid Input.");
                }
                calendarModel = new CalendarModel(month, koDay);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printLine(e.getMessage());
            }
        } while (true);
    }

    private void inputWeekdayWorkerList(String prompt) {
        do {
            String line = inputView.getLine(prompt);
            List<String> workerList = Arrays.stream(line.split("[,|\\s]+"))
                    .map(String::trim)
                    .toList();
            try {
                ValidService.isValidWorkerList(workerList);
                this.weekDayWorkerModel = new WorkerModel(workerList);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printLine(e.getMessage());
            }
        } while (true);
    }

    private void inputHolidayWorkerList(String prompt) {
        do {
            String line = inputView.getLine(prompt);
            List<String> workerList = Arrays.stream(line.split("[,|\\s]+"))
                    .map(String::trim)
                    .toList();
            try {
                ValidService.isValidWorkerList(workerList);
                this.holidayWorkerModel = new WorkerModel(workerList);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printLine(e.getMessage());
            }
        } while (true);
    }
}
