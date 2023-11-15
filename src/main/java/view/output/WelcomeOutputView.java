package view.output;

import view.output.writer.Writer;

public class WelcomeOutputView extends OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public WelcomeOutputView(final Writer writer) {
        super(writer);
    }

    public void printWelcomeMessage() {
        writer.println(WELCOME_MESSAGE);
    }

}
