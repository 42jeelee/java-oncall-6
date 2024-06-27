package oncall;

import oncall.controller.OncallController;
import oncall.view.OnCallInputView;
import oncall.view.OnCallOutputView;

public class Application {
    public static void main(String[] args) {
        OncallController controller = new OncallController(new OnCallInputView(), new OnCallOutputView());

        controller.onCall();
    }
}
