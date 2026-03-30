import java.util.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        User user = new User("a@test.com", "A", Role.USER);

        List<Seat> seats = List.of(
                new Seat("S1", SeatType.GOLD),
                new Seat("S2", SeatType.GOLD)
        );

        Movie movie = new Movie("M1", "Inception");
        Show show = new Show("SH1", movie, seats, LocalDateTime.now().plusHours(3));

        SeatLockService lockService = new SeatLockService(new InMemorySeatLockRepository());

        PricingService pricing = new PricingService();
        pricing.addStrategy(new SeatPricingStrategy());

        PaymentService payment = new PaymentServiceImpl();

        BookingService bookingService =
                new BookingService(lockService, payment, pricing);

        // BOOK
        Booking booking = bookingService.confirmBooking(user, show, seats);
        System.out.println("Booking successful!");

        // CANCEL
        CancellationService cancellationService =
                new CancellationService(
                        new TimeBasedCancellationStrategy(),
                        payment,
                        lockService
                );

        cancellationService.cancelBooking(booking);

        System.out.println("Booking cancelled and refunded!");
    }
}