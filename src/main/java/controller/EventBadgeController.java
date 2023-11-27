package controller;

import domain.event.badge.EventBadgeType;
import domain.price.DiscountPrice;
import domain.price.TotalDiscountPrice;
import view.output.EventBadgeOutputView;
import view.output.writer.Writer;

public class EventBadgeController extends Controller {
    private final EventBadgeOutputView eventBadgeOutputView;

    public EventBadgeController(final Writer writer) {
        super(writer, null);
        eventBadgeOutputView = new EventBadgeOutputView(writer);
    }

    public void calculateEventBadge(final TotalDiscountPrice totalDiscountPrice) {
        final DiscountPrice discountPrice = totalDiscountPrice.getDiscountPrice();
        final EventBadgeType eventBadgeType = EventBadgeType.findByDiscountPrice(discountPrice);
        eventBadgeOutputView.printEventBadge(eventBadgeType);
    }
}
