package hibernate.lesson4.Demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

public class HotelDemo {
    public static void main(String[] args) throws Exception {
        HotelController controller = new HotelController();
        UserController userController = new UserController();


//        User user = new User();
//        user.setUserName("Test");
//        user.setPassword("Test123");
//        user.setCountry("Ukraine");
//        user.setUserType(UserType.ADMIN);
//        user.setOrders(null);
//
//        userController.registerUser(user);

        Hotel hotel = new Hotel();
        hotel.setCity("Norway");
        hotel.setCountry("Oslo");
        hotel.setName("ter768n");
        hotel.setStreet("Norway222223");

        controller.addHotel(hotel);

//        Hotel hotel = new Hotel();
//        hotel.setCity("Norway");
//        hotel.setCountry("Oslo");
//        hotel.setName("ter768n");
//        hotel.setStreet("Norway222223");
//        Hotel hotel2 = new Hotel(44444444, "gfhd", "hsrt", "te534zen", "NorwayV77776");
//        Hotel hotel3 = new Hotel(655555555, "55555", "Oslo", "tehdszen", "222222222");
//        Hotel hotel4 = new Hotel(377777777, "qqqq", "Ogerto", "trtyhn", "Norwayurtyurt");
    }
}
