public class CryptoValidator extends TradeValidator {
    @Override
    protected boolean checkTradingHours(Tradable tradable) {
        return true; // Crypto trades 24/7
    }
}
