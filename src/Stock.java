public class Stock extends Tradable {
    public Stock(String symbol, String name, double price) {
        super(symbol, name, price);
    }

    @Override
    public String getType() {
        return "Stock";
    }
}
