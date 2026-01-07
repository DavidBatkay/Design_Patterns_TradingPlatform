public class Crypto extends Tradable {
    public Crypto(String symbol, String name, double price) {
        super(symbol, name, price);
    }

    @Override
    public String getType() {
        return "Crypto";
    }
}
