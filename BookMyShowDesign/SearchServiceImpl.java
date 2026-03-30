import java.util.List;
import java.util.stream.Collectors;

public class SearchServiceImpl implements SearchService {

    private List<Theatre> theatres;
    private List<Show> shows;

    public SearchServiceImpl(List<Theatre> theatres, List<Show> shows) {
        this.theatres = theatres;
        this.shows = shows;
    }

    @Override
    public List<Movie> getMoviesByCity(String city) {
        return shows.stream()
                .filter(s -> s.getScreen().getTheatre().getCity().equals(city))
                .map(Show::getMovie)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Theatre> getTheatresByCity(String city) {
        return theatres.stream()
                .filter(t -> t.getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Theatre> getTheatresByMovie(String city, String movieId) {
        return shows.stream()
                .filter(s -> s.getMovie().getId().equals(movieId))
                .filter(s -> s.getScreen().getTheatre().getCity().equals(city))
                .map(s -> s.getScreen().getTheatre())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByTheatre(String theatreId) {
        return shows.stream()
                .filter(s -> s.getScreen().getTheatre().getId().equals(theatreId))
                .map(Show::getMovie)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Show> getShows(String movieId, String theatreId) {
        return shows.stream()
                .filter(s -> s.getMovie().getId().equals(movieId))
                .filter(s -> s.getScreen().getTheatre().getId().equals(theatreId))
                .collect(Collectors.toList());
    }
}
