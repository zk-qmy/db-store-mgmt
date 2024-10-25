package register;
import register.users.Users;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Session {
    private static Session instance;
    // Thread-safe map to hold user sessions
    private Map<String, Users> userSessions;
    private Session() {
        userSessions = new ConcurrentHashMap<>();
    }

    public static Session getInstance() {
        if (instance==null) {
            synchronized (Session.class) {
                if(instance == null) {
                    instance = new Session();
                }
            }
        }
        return instance;
    }

    // Add a user session
    public void setUserSession(String username, Users user) {
        userSessions.put(username, user);
    }

    // Retrieve a user session by username
    public Users getUserSession(String username) {
        return userSessions.get(username);
    }

    // Remove a user session (e.g., when logging out)
    public void invalidateSession(String username) {
        userSessions.remove(username);
    }
}