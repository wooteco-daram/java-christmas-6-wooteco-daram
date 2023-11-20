package view.output;

import domain.event.EventConstants;
import view.output.writer.Writer;

public class WelcomeOutputView extends OutputView {
    private static final String WELCOME_MESSAGE_FORMAT = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.";

    public WelcomeOutputView(final Writer writer) {
        super(writer);
    }

    public void printWelcomeMessage() {
        writer.println(WELCOME_MESSAGE_FORMAT.formatted(EventConstants.EVENT_MONTH));
    }

}
