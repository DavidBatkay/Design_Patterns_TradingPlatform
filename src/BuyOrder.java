//NOTE Command Pattern
public class BuyOrder implements Order {
    private User user;
    private Tradable tradable;
    private int quantity;
    private TradingStrategy strategy;
    private TradeValidator validator;
    private double executionPrice;

    public BuyOrder(User user, Tradable tradable, int quantity, TradingStrategy strategy) {
        this.user = user;
        this.tradable = tradable;
        this.quantity = quantity;
        this.strategy = strategy;
        if (tradable instanceof Stock) {
            this.validator = new StockValidator();
        } else {
            this.validator = new CryptoValidator();
        }
    }

    @Override
    public void execute() {
        if (validator.validate(user, tradable, quantity, true)) {
            this.executionPrice = tradable.getPrice();
            double cost = executionPrice * quantity;
            user.getPortfolio().deductFunds(cost);
            user.getPortfolio().addItem(tradable, quantity, strategy);
            Market.getInstance().updateQuantity(tradable.getSymbol(), -quantity);
            System.out.println("Bought " + quantity + " of " + tradable.getName());
        }
    }
    
    @Override
    public String toString() {
        return "BUY: " + quantity + " " + tradable.getSymbol() + " @ " + executionPrice;
    }
}
