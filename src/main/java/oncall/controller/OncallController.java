package oncall.controller;

import oncall.model.KoDay;
import oncall.view.OnCallInputView;
import oncall.view.OnCallOutputView;

import java.util.Arrays;
import java.util.List;

public class OncallController {
    final private OnCallInputView inputView;
    final private OnCallOutputView outputView;

    public OncallController(OnCallInputView inputView, OnCallOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void onCall() {
        inputData();
    }

    private void inputData() {
        inputFirstLine();
        inputWorkerList("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        inputWorkerList("주말 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    private void inputFirstLine() {
        String line = inputView.getLine("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        try {
            int month = Integer.parseInt(line.split("[,|\\s]+")[0].trim());
            KoDay koDay = KoDay.valueOf(line.split("[,|\\s]+")[1].trim());

            if (month < 1 || month > 12) {
                throw new IllegalArgumentException();
            }
            /*
            * Calendar Service
            * */
            outputView.printLine(month + ", " + koDay.name());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] Invalid Input.");
        }
    }

    private void inputWorkerList(String prompt) {
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
        /*
        * WorkerList Service
        * */
        outputView.printList(workerList);
    }
}
