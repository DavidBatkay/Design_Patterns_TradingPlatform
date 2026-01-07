//NOTE Observer Pattern
public class AutoTrader implements Observer {
    private User user;
    private Broker broker;
    private double buyThreshold;
    private double sellThreshold;
    private String symbol;

    public AutoTrader(User user, Broker broker, String symbol, double buyThreshold, double sellThreshold) {
        this.user = user;
        this.broker = broker;
        this.symbol = symbol;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }

    @Override
    public void update(Tradable tradable) {
        if (!tradable.getSymbol().equals(symbol)) return;

        if (tradable.getPrice() < buyThreshold) {
            // Auto buy 1 unit
            broker.placeOrder(new BuyOrder(user, tradable, 1, new DayTradingStrategy()));
            System.out.println("Auto-bought " + tradable.getName());
        } else if (tradable.getPrice() > sellThreshold) {
            // Auto sell 1 unit if owned
            PortfolioItem item = user.getPortfolio().getItem(symbol);
            if (item != null && item.getQuantity() > 0) {
                broker.placeOrder(new SellOrder(user, tradable, 1));
                System.out.println("Auto-sold " + tradable.getName());
            }
        }
    }
}
