//NOTE Decorator Pattern
public class FeeDecorator extends TradableDecorator {
    private double feePercentage;

    public FeeDecorator(Tradable tradable, double feePercentage) {
        super(tradable);
        this.feePercentage = feePercentage;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1 + feePercentage);
    }
}
