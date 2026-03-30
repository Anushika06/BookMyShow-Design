import java.util.*;

public class BookingService {

    private SeatLockService lockService;
    private PaymentService paymentService;
    private PricingService pricingService;

    public BookingService(SeatLockService lockService,
                          PaymentService paymentService,
                          PricingService pricingService) {
        this.lockService = lockService;
        this.paymentService = paymentService;
        this.pricingService = pricingService;
    }

    public Booking confirmBooking(User user, Show show, List<Seat> seats) {

    boolean locked = lockService.tryLockSeats(show, seats, user);

    if (!locked) {
        throw new RuntimeException("Seats unavailable. Retry.");
    }

    double price = pricingService.calculateTotal(show, seats);

    try {
        Payment payment = paymentService.pay(user, price);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {

            for (Seat seat : seats) {
                show.updateSeatStatus(seat.getId(), SeatStatus.BOOKED);
            }

            Booking booking = new Booking(
                    UUID.randomUUID().toString(),
                    user,
                    show,
                    seats,
                    price,
                    payment
            );

            booking.confirm();
            return booking;
        }

    } catch (Exception e) {
        lockService.unlockSeats(show, seats);
        throw e;
    }

    lockService.unlockSeats(show, seats);
    throw new RuntimeException("Payment failed");
}
}