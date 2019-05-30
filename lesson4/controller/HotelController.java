package hibernate.lesson4.controller;

import hibernate.Exeptions.BadRequestExeption;
import hibernate.lesson4.Services.HotelService;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Session;
import hibernate.lesson4.model.UserType;

import java.util.Collection;

public class HotelController {

    private HotelService hotelService = new HotelService();

    public Collection findHotelByName(String name) throws BadRequestExeption {
        if (Session.getUser() != null)
            return hotelService.findHotelByName(name);
        else throw new BadRequestExeption("You are not logged in");
    }

    public Collection findHotelByCity(String city) throws BadRequestExeption {
        if (Session.getUser() != null)
            return hotelService.findHotelByCity(city);
        else throw new BadRequestExeption("You are not logged in");
    }

    public Hotel addHotel(Hotel hotel) throws Exception {
       // if (Session.getUser() != null && Session.getUser().getUserType() != UserType.ADMIN)
            return hotelService.addHotel(hotel);
       // else throw new BadRequestExeption("You are not logged in or you have no permission for this operation");
    }

    public Hotel deleteHotel(long hotelId) throws Exception {
        if (Session.getUser() != null && Session.getUser().getUserType() != UserType.ADMIN)
            return hotelService.deleteHotel(hotelId);
        else throw new BadRequestExeption("You are not logged in or you have no permission for this operation");
    }
}
