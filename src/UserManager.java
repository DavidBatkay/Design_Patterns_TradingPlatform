import java.util.HashMap;
import java.util.Map;

//NOTE Singleton Pattern
public class UserManager {
    private static UserManager instance;
    private Map<String, User> users;
    private User currentUser;

    private UserManager() {
        users = new HashMap<>();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void register(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
        }
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
