public class SeatLock {
    String showId;
    String seatId;
    String userId;
    long expiryTime;

    public SeatLock(String showId, String seatId, String userId, long expiryTime) {
        this.showId = showId;
        this.seatId = seatId;
        this.userId = userId;
        this.expiryTime = expiryTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
