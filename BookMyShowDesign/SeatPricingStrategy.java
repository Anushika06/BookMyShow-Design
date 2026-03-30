import java.util.List;

public class SeatPricingStrategy implements PricingStrategy {
    public double calculate(Show show, List<Seat> seats) {
        return seats.size() * 100;
    }
}
