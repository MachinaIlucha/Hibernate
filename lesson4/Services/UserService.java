package hibernate.lesson4.Services;

import hibernate.lesson4.Repository.UserRepository;
import hibernate.lesson4.model.User;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) {
        return userRepository.registerUser(user);
    }

    public void login(String userName, String password) {
        userRepository.login(userName, password);
    }

    public void logout() {
        userRepository.logout();
    }
}
