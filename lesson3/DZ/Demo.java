package hibernate.lesson3.DZ;

public class Demo {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.setCity("olol");
        hotel.setCountry("gdrs");
        hotel.setName("gggraaa");
        hotel.setStreet("hrstn");

        HotelDAO.save(hotel);
    }
}
