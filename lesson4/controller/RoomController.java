package hibernate.lesson4.controller;

import hibernate.Exeptions.BadRequestExeption;
import hibernate.lesson4.Services.RoomService;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.Session;
import hibernate.lesson4.model.UserType;

import java.util.Collection;

public class RoomController {

    private RoomService roomService = new RoomService();

    public Collection findRooms(Filter filter) throws BadRequestExeption {
        if (Session.getUser() != null)
            return roomService.findRooms(filter);
        else throw new BadRequestExeption("You are not logged in");
    }

    public Room addRoom(Room room) throws Exception {
        if (Session.getUser() != null && Session.getUser().getUserType() != UserType.ADMIN)
            return roomService.addRoom(room);
        else throw new BadRequestExeption("You are not logged in or you have no permission for this operation");
    }

    public void deleteRoom(long roomId) throws Exception {
        if (Session.getUser() != null && Session.getUser().getUserType() != UserType.ADMIN)
            roomService.deleteRoom(roomId);
        else throw new BadRequestExeption("You are not logged in or you have no permission for this operation");
    }
}
