class Payment {
    private String paymentId;
    private double amount;
    private PaymentStatus status;
    private PaymentMethod method;
    private String transactionId; 
    private String provider; 

    public Payment(String paymentId, double amount, PaymentStatus status,
                   PaymentMethod method, String transactionId, String provider) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.transactionId = transactionId;
        this.provider = provider;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}