interface PaymentService {
    Payment pay(User user, double amount);
    void refund(Payment payment, double amount);
}