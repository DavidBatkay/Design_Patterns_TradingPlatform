public class User {
    private String username;
    private String password;
    private Portfolio portfolio;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
