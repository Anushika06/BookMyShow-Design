import java.util.*;

public class PricingService {
    private List<PricingStrategy> strategies = new ArrayList<>();

    public void addStrategy(PricingStrategy strategy) {
        strategies.add(strategy);
    }

    public double calculateTotal(Show show, List<Seat> seats) {
        double total = 0;
        for (PricingStrategy s : strategies) {
            total += s.calculate(show, seats);
        }
        return total;
    }
}