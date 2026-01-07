//NOTE Strategy Pattern
public class DayTradingStrategy implements TradingStrategy {
    @Override
    public double calculateFee(Tradable tradable, int quantity, long purchaseTime) {
        long currentTime = System.currentTimeMillis();
        // Simple check: if more than 24 hours (86400000 ms) have passed, apply 5% fee
        // In a real app, we'd check calendar days.
        if (currentTime - purchaseTime > 86400000) {
            return tradable.getPrice() * quantity * 0.05;
        }
        return 0.0;
    }

    @Override
    public String getStrategyName() {
        return "Day Trading";
    }
}
