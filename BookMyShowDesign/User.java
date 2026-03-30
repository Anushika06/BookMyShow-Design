public class User {
    private String email;
    private String name;
    private Role role;

    public User(String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public String getEmail() {
        return email;
    }
    public String getName(){
        return name;
    }
}