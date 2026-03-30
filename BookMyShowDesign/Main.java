import java.util.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- WELCOME TO BOOKMYSHOW DESIGN ---");

        User user1 = new User("alice@example.com", "Alice", Role.USER);
        User user2 = new User("bob@example.com", "Bob", Role.USER);

        Movie inception = new Movie("M1", "Inception");
        Movie darkKnight = new Movie("M2", "The Dark Knight");

        Theatre pvrBangalore = new Theatre("T1", "PVR IMAX", "Bangalore");
        Theatre cinepolisMumbai = new Theatre("T2", "Cinepolis", "Mumbai");

        Screen screen1 = new Screen("SCR1", pvrBangalore);
        Screen screen2 = new Screen("SCR2", cinepolisMumbai);

        List<Seat> bglSeats = List.of(new Seat("S1", SeatType.GOLD), new Seat("S2", SeatType.GOLD));
        List<Seat> mumSeats = List.of(new Seat("S3", SeatType.SILVER), new Seat("S4", SeatType.SILVER));

        Show show1 = new Show("SH1", inception, bglSeats, LocalDateTime.now().plusHours(3), screen1);
        Show show2 = new Show("SH2", darkKnight, mumSeats, LocalDateTime.now().plusHours(1), screen2);

        SeatLockService lockService = new SeatLockService(new InMemorySeatLockRepository());
        PricingService pricingService = new PricingService();
        pricingService.addStrategy(new SeatPricingStrategy());
        PaymentService paymentService = new PaymentServiceImpl();
        BookingService bookingService = new BookingService(lockService, paymentService, pricingService);
        SearchService searchService = new SearchServiceImpl(List.of(pvrBangalore, cinepolisMumbai), List.of(show1, show2));

        System.out.println("\n--- FLOW 1: DISCOVERY -> CHOOSING (SUCCESSFUL BOOKING) ---");
        
        // Step 1: Discovery - Get Movies in Bangalore
        System.out.println("[Step 1] Alice is browsing movies in Bangalore...");
        List<Movie> moviesInBangalore = searchService.getMoviesByCity("Bangalore");
        Movie selectedMovie = moviesInBangalore.get(0);
        System.out.println("   -> Found: " + selectedMovie.getName());

        // Step 2: Choosing - Select the show for that movie in PVR
        System.out.println("[Step 2] Alice is checking shows for " + selectedMovie.getName() + " in PVR Bangalore...");
        List<Show> availableShows = searchService.getShows(selectedMovie.getId(), pvrBangalore.getId());
        Show selectedShow = availableShows.get(0);
        System.out.println("   -> Selected Show: " + selectedShow.getId() + " at " + selectedShow.getStartTime());

        // Step 3: Confirmation - Confirm booking after seat selection
        System.out.println("[Step 3] Alice selects seats and confirms booking...");
        Booking booking1 = bookingService.confirmBooking(user1, selectedShow, bglSeats);
        System.out.println("   -> Booking Successful! ID: " + booking1.getId());
        System.out.println("   -> Total Amount Paid: " + booking1.getAmount());

        System.out.println("\n--- FLOW 2: BOOKING -> CANCELLATION & REFUND ---");

        // Step 1: Bob decides to book a ticket for The Dark Knight in Mumbai
        System.out.println("[Step 1] Bob is booking a ticket for The Dark Knight in Mumbai...");
        Booking booking2 = bookingService.confirmBooking(user2, show2, mumSeats);
        System.out.println("   -> Booking Successful! ID: " + booking2.getId());

        // Step 2: Bob realizes he cannot attend and decides to cancel
        System.out.println("[Step 2] Bob requests cancellation for his booking...");
        CancellationService cancellationService = new CancellationService(
                new TimeBasedCancellationStrategy(),
                paymentService,
                lockService
        );

        cancellationService.cancelBooking(booking2);
        System.out.println("   -> Cancellation SUCCESSFUL. Please check your account for the refund.");
        System.out.println("   -> Booking Status: " + (booking2.getStatus()));

        System.out.println("\n--- THANK YOU FOR VISITING BOOKMYSHOW ---");
    }
}