public class Screen {
    private String id;
    private Theatre theatre;

    public Screen(String id, Theatre theatre) {
        this.id = id;
        this.theatre = theatre;
    }

    public String getId() {
        return id;
    }

    public Theatre getTheatre() {
        return theatre;
    }
}
