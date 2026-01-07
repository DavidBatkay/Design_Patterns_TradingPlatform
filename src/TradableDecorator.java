public abstract class TradableDecorator extends Tradable {
    protected Tradable decoratedTradable;

    public TradableDecorator(Tradable tradable) {
        super(tradable.getSymbol(), tradable.getName(), tradable.getPrice());
        this.decoratedTradable = tradable;
    }

    @Override
    public double getPrice() {
        return decoratedTradable.getPrice();
    }

    @Override
    public String getType() {
        return decoratedTradable.getType();
    }
}
