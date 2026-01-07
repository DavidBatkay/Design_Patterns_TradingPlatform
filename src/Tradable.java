public abstract class Tradable {
    protected String symbol;
    protected String name;
    protected double price;

    public Tradable(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + ": " + name + " (" + symbol + ") - $" + price;
    }
}
