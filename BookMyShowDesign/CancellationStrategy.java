interface CancellationStrategy {
    boolean isAllowed(Booking booking);
    double refundAmount(Booking booking);
}