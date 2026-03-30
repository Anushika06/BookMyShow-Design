import java.util.HashMap;
import java.util.Map;

class InMemorySeatLockRepository implements SeatLockRepository {

    private Map<String, SeatLock> locks = new HashMap<>();

    @Override
    public void save(SeatLock lock) {
        locks.put(lock.showId + ":" + lock.seatId, lock);
    }

    @Override
    public void remove(String key) {
        locks.remove(key);
    }

    @Override
    public SeatLock get(String key) {
        return locks.get(key);
    }
}