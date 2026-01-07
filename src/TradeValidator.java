//NOTE Template Method Pattern
public abstract class TradeValidator {
    public final boolean validate(User user, Tradable tradable, int quantity, boolean isBuy) {
        if (!checkTradingHours(tradable)) {
            System.out.println("Trading is closed for " + tradable.getName());
            return false;
        }
        if (isBuy) {
            if (!checkFunds(user, tradable, quantity)) {
                System.out.println("Insufficient funds.");
                return false;
            }
            if (!checkAvailability(tradable, quantity)) {
                System.out.println("Insufficient market quantity.");
                return false;
            }
        } else {
            if (!checkOwnership(user, tradable, quantity)) {
                System.out.println("You do not own enough of this item.");
                return false;
            }
        }
        return true;
    }

    protected abstract boolean checkTradingHours(Tradable tradable);

    private boolean checkFunds(User user, Tradable tradable, int quantity) {
        return user.getPortfolio().getBalance() >= tradable.getPrice() * quantity;
    }

    private boolean checkAvailability(Tradable tradable, int quantity) {
        return Market.getInstance().getQuantity(tradable.getSymbol()) >= quantity;
    }

    private boolean checkOwnership(User user, Tradable tradable, int quantity) {
        PortfolioItem item = user.getPortfolio().getItem(tradable.getSymbol());
        return item != null && item.getQuantity() >= quantity;
    }
}
