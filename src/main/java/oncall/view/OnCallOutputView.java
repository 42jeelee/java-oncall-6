package oncall.view;

import java.util.List;

public class OnCallOutputView {
    public void printLine(String line) {
        System.out.println(line);
    }

    public void printList(List<String> list) {
        System.out.print(String.join("\n", list));
    }
}
