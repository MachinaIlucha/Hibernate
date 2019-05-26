package hibernate.lesson4.model;

public class Session {
    private static User userThis = null;

    public static void setUser(User user) {
        user = userThis;
    }

    public static User getUser() {
        return userThis;
    }
}
