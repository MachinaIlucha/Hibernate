package hibernate.lesson4.Services;

import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.Repository.RoomRepository;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;

import java.util.Collection;

public class RoomService {

    private RoomRepository roomRepository = new RoomRepository();

    public Collection findRooms(Filter filter) {
        return roomRepository.findRooms(filter);
    }

    public Room addRoom(Room room) {
        return RoomDAO.save(room);
    }

    public Room deleteRoom(long roomId) {
        return RoomDAO.delete(roomId);
    }
}
