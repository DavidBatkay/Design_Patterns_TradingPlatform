public class PortfolioItem {
    private Tradable tradable;
    private int quantity;
    private long purchaseTime;
    private TradingStrategy strategy;

    public PortfolioItem(Tradable tradable, int quantity, TradingStrategy strategy) {
        this.tradable = tradable;
        this.quantity = quantity;
        this.strategy = strategy;
        this.purchaseTime = System.currentTimeMillis();
    }

    public Tradable getTradable() {
        return tradable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int q) {
        this.quantity += q;
    }

    public void removeQuantity(int q) {
        this.quantity -= q;
    }

    public TradingStrategy getStrategy() {
        return strategy;
    }

    public long getPurchaseTime() {
        return purchaseTime;
    }
}
