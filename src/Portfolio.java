import java.util.HashMap;
import java.util.Map;

//NOTE Observer Pattern
public class Portfolio implements Observer {
    private Map<String, PortfolioItem> items;
    private double balance;

    public Portfolio() {
        items = new HashMap<>();
        balance = 10000.0; // Starting balance
    }

    public void addItem(Tradable t, int quantity, TradingStrategy strategy) {
        if (items.containsKey(t.getSymbol())) {
            items.get(t.getSymbol()).addQuantity(quantity);
        } else {
            items.put(t.getSymbol(), new PortfolioItem(t, quantity, strategy));
        }
    }

    public void removeItem(String symbol, int quantity) {
        if (items.containsKey(symbol)) {
            PortfolioItem item = items.get(symbol);
            item.removeQuantity(quantity);
            if (item.getQuantity() <= 0) {
                items.remove(symbol);
            }
        }
    }

    public PortfolioItem getItem(String symbol) {
        return items.get(symbol);
    }

    public Map<String, PortfolioItem> getItems() {
        return items;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        balance += amount;
    }

    public void deductFunds(double amount) {
        balance -= amount;
    }

    @Override
    public void update(Tradable tradable) {
        if (items.containsKey(tradable.getSymbol())) {
            System.out.println("Notification: Price of " + tradable.getName() + " changed to " + tradable.getPrice());
        }
    }
}
