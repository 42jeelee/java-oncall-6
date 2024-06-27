package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class OnCallInputView {
    public String getLine(String prompt) {
        System.out.print(prompt);
        return Console.readLine();
    }
}
