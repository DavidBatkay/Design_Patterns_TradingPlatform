import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//NOTE Singleton Pattern
//NOTE Observer Pattern
public class Market {
    private static Market instance;
    private Map<String, Tradable> tradables;
    private Map<String, Integer> quantities;
    private List<Observer> observers;

    private Market() {
        tradables = new HashMap<>();
        quantities = new HashMap<>();
        observers = new ArrayList<>();
    }

    public static synchronized Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }

    public void addTradable(Tradable t, int quantity) {
        tradables.put(t.getSymbol(), t);
        quantities.put(t.getSymbol(), quantity);
    }

    public Tradable getTradable(String symbol) {
        return tradables.get(symbol);
    }

    public int getQuantity(String symbol) {
        return quantities.getOrDefault(symbol, 0);
    }

    public void updateQuantity(String symbol, int delta) {
        if (quantities.containsKey(symbol)) {
            quantities.put(symbol, quantities.get(symbol) + delta);
        }
    }

    public void updatePrice(String symbol, double newPrice) {
        Tradable t = tradables.get(symbol);
        if (t != null) {
            t.setPrice(newPrice);
            notifyObservers(t);
        }
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    private void notifyObservers(Tradable t) {
        for (Observer o : observers) {
            o.update(t);
        }
    }

    public Map<String, Tradable> getAllTradables() {
        return tradables;
    }
}
