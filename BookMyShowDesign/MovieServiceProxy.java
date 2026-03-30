public class MovieServiceProxy {

    private MovieService service;

    public MovieServiceProxy(MovieService service) {
        this.service = service;
    }

    public void addMovie(User user, Movie movie) {
        if (!user.isAdmin()) {
            throw new RuntimeException("Unauthorized");
        }
        service.addMovie(movie);
    }
}