package view.output;

import domain.benefit.BenefitGroup;
import domain.price.TotalDiscountPrice;
import view.output.writer.Writer;

public class BenefitOutputView extends OutputView {
    private static final String BENEFIT_GROUP_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_MESSAGE = "<총혜택 금액>";
    public BenefitOutputView(final Writer writer) {
        super(writer);
    }

    public void printBenefitGroup(final BenefitGroup benefitGroup) {
        writer.println(BENEFIT_GROUP_MESSAGE);
        writer.println(benefitGroup.toString());
    }

    public void printTotalBenefitPriceMessage(final TotalDiscountPrice totalDiscountPrice) {
        writer.println(TOTAL_BENEFIT_PRICE_MESSAGE);
        writer.println(totalDiscountPrice.toString());
    }
}
