import java.util.*;
import java.time.LocalDateTime;

public class Show {
    private String id;
    private Movie movie;
    private LocalDateTime startTime;
    private Map<String, SeatStatus> seatStatus = new HashMap<>();

    public Show(String id, Movie movie, List<Seat> seats, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;

        for (Seat seat : seats) {
            seatStatus.put(seat.getId(), SeatStatus.AVAILABLE);
        }
    }

    public SeatStatus getSeatStatus(String seatId) {
        return seatStatus.get(seatId);
    }

    public void updateSeatStatus(String seatId, SeatStatus status) {
        seatStatus.put(seatId, status);
    }

    public String getId() {
        return id;
    }
    public Movie getMovie() {
        return movie;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
}
