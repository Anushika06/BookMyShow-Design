class CancellationService {

    private CancellationStrategy strategy;
    private PaymentService paymentService;
    private SeatLockService seatLockService;

    public CancellationService(CancellationStrategy strategy,
                               PaymentService paymentService,
                               SeatLockService seatLockService) {
        this.strategy = strategy;
        this.paymentService = paymentService;
        this.seatLockService = seatLockService;
    }

    public boolean cancelBooking(Booking booking) {

        if (!strategy.isAllowed(booking)) {
            throw new RuntimeException("Cancellation not allowed");
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Already cancelled");
}

        double refund = strategy.refundAmount(booking);

        Payment payment = booking.getPayment();
        paymentService.refund(payment, refund);

        payment.setStatus(PaymentStatus.REFUNDED);

        Show show = booking.getShow();
        for (Seat seat : booking.getSeats()) {
            show.updateSeatStatus(seat.getId(), SeatStatus.AVAILABLE);
        }

        booking.cancel();

        return true;
    }
}