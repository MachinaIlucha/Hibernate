package hibernate.lesson4.Services;

import hibernate.Exeptions.BadRequestExeption;
import hibernate.lesson4.Repository.OrderRepository;

import java.util.Date;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws BadRequestExeption {
        orderRepository.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId){
        orderRepository.cancelReservation(roomId, userId);
    }
}
