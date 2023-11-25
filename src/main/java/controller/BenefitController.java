package controller;

import domain.benefit.BenefitGroup;
import domain.benefit.discount.ChristmasDiscount;
import domain.benefit.discount.PresentDiscount;
import domain.benefit.discount.SpecialDiscount;
import domain.benefit.discount.WeekDayDiscount;
import domain.benefit.discount.WeekEndDiscount;
import domain.order.OrderGroup;
import domain.order.PresentOrderGroup;
import domain.price.TotalDiscountPrice;
import domain.price.TotalPrice;
import domain.reservation.ReservationDay;
import java.util.List;
import view.output.BenefitOutputView;
import view.output.PresentOutputView;
import view.output.writer.Writer;

public class BenefitController extends Controller {
    private final PresentOutputView presentOutputView;
    private final BenefitOutputView benefitOutputView;


    public BenefitController(final Writer writer) {
        super(writer, null);

        presentOutputView = new PresentOutputView(writer);
        benefitOutputView = new BenefitOutputView(writer);
    }

    public TotalDiscountPrice getTotalDiscountPrice(
            final ReservationDay reservationDay,
            final OrderGroup orderGroup,
            final TotalPrice totalPrice
    ) {
        final PresentOrderGroup presentOrder = calculatePresentOrder(totalPrice);
        final BenefitGroup benefitGroup = calculateBenefitGroup(
                reservationDay,
                orderGroup,
                presentOrder,
                totalPrice
        );
        return calculateTotalDiscountPrice(benefitGroup);
    }

    private PresentOrderGroup calculatePresentOrder(final TotalPrice totalPrice) {
        final PresentOrderGroup presentOrder = new PresentOrderGroup(totalPrice);
        presentOutputView.printPresentMenu(presentOrder);
        presentOutputView.printNewLine();
        return presentOrder;
    }

    private BenefitGroup calculateBenefitGroup(
            final ReservationDay reservationDay,
            final OrderGroup orderGroup,
            final PresentOrderGroup presentOrderGroup,
            final TotalPrice totalPrice
    ) {
        final BenefitGroup benefitGroup = new BenefitGroup(List.of(
                new ChristmasDiscount(reservationDay),
                new WeekDayDiscount(reservationDay, orderGroup),
                new WeekEndDiscount(reservationDay, orderGroup),
                new SpecialDiscount(reservationDay),
                new PresentDiscount(presentOrderGroup.getTotalDiscountPrice())
        ), totalPrice);
        benefitOutputView.printBenefitGroup(benefitGroup);
        benefitOutputView.printNewLine();
        return benefitGroup;
    }

    private TotalDiscountPrice calculateTotalDiscountPrice(final BenefitGroup benefitGroup) {
        final TotalDiscountPrice totalDiscountPrice = benefitGroup.getTotalDiscountPrice();
        benefitOutputView.printTotalBenefitPriceMessage(totalDiscountPrice);
        benefitOutputView.printNewLine();
        return totalDiscountPrice;
    }
}
