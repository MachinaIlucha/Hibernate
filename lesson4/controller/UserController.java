package hibernate.lesson4.controller;

import hibernate.Exeptions.BadRequestExeption;
import hibernate.lesson4.Services.UserService;
import hibernate.lesson4.model.Session;
import hibernate.lesson4.model.User;

public class UserController {

    private UserService userService = new UserService();

    public User registerUser(User user) throws Exception {
        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        userService.login(userName, password);
    }

    public void logout() throws BadRequestExeption {
        if (Session.getUser() != null)
            userService.logout();
        else throw new BadRequestExeption("Sorry but you already left");
    }
}
