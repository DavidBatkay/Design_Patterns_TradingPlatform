//NOTE Strategy Pattern
public interface TradingStrategy {
    double calculateFee(Tradable tradable, int quantity, long purchaseTime);
    String getStrategyName();
}
