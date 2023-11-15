package view.output;

import domain.event.EventConstants;
import domain.event.badge.EventBadgeType;
import domain.price.TotalDiscountPrice;
import view.output.writer.Writer;

public class EventBadgeOutputView extends OutputView {
    private static final String EVENT_BADGE_MESSAGE_FORMAT = "<%d월 이벤트 배지>";
    public EventBadgeOutputView(final Writer writer) {
        super(writer);
    }

    public void printEventBadge(final EventBadgeType eventBadgeType) {
        writer.println(EVENT_BADGE_MESSAGE_FORMAT.formatted(EventConstants.EVENT_MONTH));
        writer.println(eventBadgeType.toString());
    }
}
