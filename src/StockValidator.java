import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class StockValidator extends TradeValidator {
    @Override
    protected boolean checkTradingHours(Tradable tradable) {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek day = now.getDayOfWeek();
        LocalTime time = now.toLocalTime();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }
        return time.isAfter(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(18, 0));
    }
}
