//NOTE Strategy Pattern
public class LongTermStrategy implements TradingStrategy {
    @Override
    public double calculateFee(Tradable tradable, int quantity, long purchaseTime) {
        return 0.0; // No extra fee for long term
    }

    @Override
    public String getStrategyName() {
        return "Long Term";
    }
}
