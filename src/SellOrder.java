//NOTE Command Pattern
public class SellOrder implements Order {
    private User user;
    private Tradable tradable;
    private int quantity;
    private TradeValidator validator;
    private double executionPrice;

    public SellOrder(User user, Tradable tradable, int quantity) {
        this.user = user;
        this.tradable = tradable;
        this.quantity = quantity;
        if (tradable instanceof Stock) {
            this.validator = new StockValidator();
        } else {
            this.validator = new CryptoValidator();
        }
    }

    @Override
    public void execute() {
        if (validator.validate(user, tradable, quantity, false)) {
            this.executionPrice = tradable.getPrice();
            PortfolioItem item = user.getPortfolio().getItem(tradable.getSymbol());
            double fee = item.getStrategy().calculateFee(tradable, quantity, item.getPurchaseTime());
            double revenue = (executionPrice * quantity) - fee;
            
            user.getPortfolio().addFunds(revenue);
            user.getPortfolio().removeItem(tradable.getSymbol(), quantity);
            Market.getInstance().updateQuantity(tradable.getSymbol(), quantity);
            System.out.println("Sold " + quantity + " of " + tradable.getName() + ". Fee: " + fee);
        }
    }

    @Override
    public String toString() {
        return "SELL: " + quantity + " " + tradable.getSymbol() + " @ " + executionPrice;
    }
}
