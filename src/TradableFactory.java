//NOTE Factory Method Pattern
public class TradableFactory {
    public static Tradable createTradable(String type, String symbol, String name, double price) {
        if (type.equalsIgnoreCase("stock")) {
            return new Stock(symbol, name, price);
        } else if (type.equalsIgnoreCase("crypto")) {
            return new Crypto(symbol, name, price);
        }
        throw new IllegalArgumentException("Unknown tradable type");
    }
}
