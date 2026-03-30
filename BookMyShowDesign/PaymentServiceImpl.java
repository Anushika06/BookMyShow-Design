import java.util.UUID;

class PaymentServiceImpl implements PaymentService {

    @Override
    public Payment pay(User user, double amount) {

        String txnId = UUID.randomUUID().toString();

        return new Payment(
                UUID.randomUUID().toString(),
                amount,
                PaymentStatus.SUCCESS,
                PaymentMethod.UPI,
                txnId,
                "Razorpay"
        );
    }

    @Override
    public void refund(Payment payment, double amount) {

        System.out.println("Refunding " + amount +
                " using txnId: " + payment.getTransactionId());

    }
}