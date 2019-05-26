package hibernate.lesson4.Services;

import hibernate.lesson4.DAO.HotelDAO;
import hibernate.lesson4.Repository.HotelRepository;
import hibernate.lesson4.model.Hotel;

import java.util.Collection;

public class HotelService {

    private HotelRepository hotelRepository = new HotelRepository();

    public Collection findHotelByName(String name) {
        return hotelRepository.findHotelByName(name);
    }

    public Collection findHotelByCity(String city) {
        return hotelRepository.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) {
        return HotelDAO.save(hotel);
    }

    public Hotel deleteHotel(long hotelId) {
        return HotelDAO.delete(hotelId);
    }
}
