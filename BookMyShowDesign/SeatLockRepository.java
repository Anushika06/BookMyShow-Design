interface SeatLockRepository {
    void save(SeatLock lock);
    void remove(String key);
    SeatLock get(String key);
}

