import java.util.Map;

//NOTE Facade Pattern
public class TradingSystemFacade {
    private Market market;
    private UserManager userManager;
    private Broker broker;

    public TradingSystemFacade() {
        market = Market.getInstance();
        userManager = UserManager.getInstance();
        broker = new Broker();
        initializeMarket();
    }

    private void initializeMarket() {
        market.addTradable(TradableFactory.createTradable("stock", "AAPL", "Apple Inc.", 150.0), 1000);
        market.addTradable(TradableFactory.createTradable("stock", "GOOGL", "Alphabet Inc.", 2800.0), 500);
        market.addTradable(TradableFactory.createTradable("crypto", "BTC", "Bitcoin", 45000.0), 100);
        market.addTradable(TradableFactory.createTradable("crypto", "ETH", "Ethereum", 3000.0), 200);
    }

    public void registerUser(String username, String password) {
        userManager.register(username, password);
    }

    public boolean loginUser(String username, String password) {
        return userManager.login(username, password);
    }

    public void logoutUser() {
        userManager.logout();
    }

    public User getCurrentUser() {
        return userManager.getCurrentUser();
    }

    public void buy(String symbol, int quantity, String strategyType) {
        User user = getCurrentUser();
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        Tradable t = market.getTradable(symbol);
        if (t == null) {
            System.out.println("Item not found.");
            return;
        }
        TradingStrategy strategy;
        if (strategyType.equalsIgnoreCase("day")) {
            strategy = new DayTradingStrategy();
        } else {
            strategy = new LongTermStrategy();
        }
        broker.placeOrder(new BuyOrder(user, t, quantity, strategy));
    }

    public void sell(String symbol, int quantity) {
        User user = getCurrentUser();
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        Tradable t = market.getTradable(symbol);
        if (t == null) {
            System.out.println("Item not found.");
            return;
        }
        broker.placeOrder(new SellOrder(user, t, quantity));
    }

    public void printMarket() {
        System.out.println("--- Market ---");
        for (Map.Entry<String, Tradable> entry : market.getAllTradables().entrySet()) {
            System.out.println(entry.getValue() + " | Qty: " + market.getQuantity(entry.getKey()));
        }
    }

    public void printPortfolio() {
        User user = getCurrentUser();
        if (user == null) return;
        System.out.println("--- Portfolio for " + user.getUsername() + " ---");
        System.out.println("Balance: $" + user.getPortfolio().getBalance());
        for (PortfolioItem item : user.getPortfolio().getItems().values()) {
            System.out.println(item.getTradable().getName() + ": " + item.getQuantity() + " (" + item.getStrategy().getStrategyName() + ")");
        }
    }

    public void printHistory() {
        System.out.println("--- Transaction History ---");
        for (Order order : broker.getHistory()) {
            System.out.println(order);
        }
    }
    
    public void setAutoTrading(String symbol, double buyAt, double sellAt) {
        User user = getCurrentUser();
        if (user != null) {
            AutoTrader auto = new AutoTrader(user, broker, symbol, buyAt, sellAt);
            market.addObserver(auto);
            System.out.println("Auto trading set for " + symbol);
        }
    }
    
    public void simulatePriceChange(String symbol, double newPrice) {
        market.updatePrice(symbol, newPrice);
    }
}
