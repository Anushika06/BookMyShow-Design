import java.util.List;

class SeatLockService {

    private SeatLockRepository repo;
    private static final long LOCK_DURATION = 10 * 60 * 1000; // 10 mins

    public SeatLockService(SeatLockRepository repo) {
        this.repo = repo;
    }

    public synchronized boolean tryLockSeats(Show show, List<Seat> seats, User user) {

        for (Seat seat : seats) {
            String key = show.getId() + ":" + seat.getId();
            SeatLock existing = repo.get(key);

            if (existing != null && !existing.isExpired()) {
                return false;
            }

            if (show.getSeatStatus(seat.getId()) != SeatStatus.AVAILABLE) {
                return false;
            }
        }

        for (Seat seat : seats) {
            SeatLock lock = new SeatLock(
                    show.getId(),
                    seat.getId(),
                    user.getEmail(),
                    System.currentTimeMillis() + LOCK_DURATION
            );
            repo.save(lock);
            show.updateSeatStatus(seat.getId(), SeatStatus.LOCKED);
        }

        return true;
    }

    public void unlockSeats(Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            String key = show.getId() + ":" + seat.getId();
            repo.remove(key);
            show.updateSeatStatus(seat.getId(), SeatStatus.AVAILABLE);
        }
    }
}