import java.util.List;

public interface SearchService {

    List<Movie> getMoviesByCity(String city);

    List<Theatre> getTheatresByCity(String city);

    List<Theatre> getTheatresByMovie(String city, String movieId);

    List<Movie> getMoviesByTheatre(String theatreId);

    List<Show> getShows(String movieId, String theatreId);
}
