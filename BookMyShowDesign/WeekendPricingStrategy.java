import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy {
    public double calculate(Show show, List<Seat> seats) {
        return 50; 
    }
}