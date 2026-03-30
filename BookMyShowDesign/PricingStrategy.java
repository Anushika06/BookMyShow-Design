import java.util.List;

interface PricingStrategy {
    double calculate(Show show, List<Seat> seats);
}