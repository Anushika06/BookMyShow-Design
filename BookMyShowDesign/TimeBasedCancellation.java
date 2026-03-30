import java.time.Duration;
import java.time.LocalDateTime;

class TimeBasedCancellationStrategy implements CancellationStrategy {

    private static final int ALLOWED_HOURS = 2; // before show

    @Override
    public boolean isAllowed(Booking booking) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime showTime = booking.getShow().getStartTime();

        return Duration.between(now, showTime).toHours() >= ALLOWED_HOURS;
    }

    @Override
    public double refundAmount(Booking booking) {
        return booking.getAmount(); // full refund (can change)
    }
}