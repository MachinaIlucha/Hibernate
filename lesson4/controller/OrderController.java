package hibernate.lesson4.controller;

import hibernate.Exeptions.BadRequestExeption;
import hibernate.lesson4.Services.OrderService;
import hibernate.lesson4.model.Session;

import java.util.Date;

public class OrderController {

    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws BadRequestExeption {
        if (Session.getUser() != null)
            orderService.bookRoom(roomId, userId, dateFrom, dateTo);
        else throw new BadRequestExeption("You are not logged in");
    }

    public void cancelReservation(long roomId, long userId) throws BadRequestExeption {
        if (Session.getUser() != null)
            orderService.cancelReservation(roomId, userId);
        else throw new BadRequestExeption("You are not logged in");
    }
}
