package hibernate.lesson3.DZ;

public class Demo {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.setCity("Kiev");
        hotel.setCountry("Ukraine");
        hotel.setName("Dominic444");
        hotel.setStreet("Obolon");

        HotelDAO.save(hotel);
    }
}
