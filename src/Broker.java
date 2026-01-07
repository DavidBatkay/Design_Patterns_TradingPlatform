import java.util.ArrayList;
import java.util.List;

//NOTE Command Pattern (Invoker)
// Executes orders and maintains a history of transactions.
public class Broker {
    private List<Order> history = new ArrayList<>();

    public void placeOrder(Order order) {
        order.execute();
        history.add(order);
    }

    public List<Order> getHistory() {
        return history;
    }
}
