package hibernate.lesson3.DZ;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOMS")
public class Room {

    private long room_id;
    private int numberofguest;
    private double price;
    private int breakfastincluded;
    private int petsallowed;
    private Date dateavaible;

    private Hotel hotel;

    public Room() {
    }

    @Id
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    @Column(name = "ROOM_ID")
    public long getRoom_id() {
        return room_id;
    }

    @Column(name = "NUMBEROFGUESTS")
    public int getNumberofguest() {
        return numberofguest;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @Column(name = "BREAKFASTINCLUDED")
    public int getBreakfastincluded() {
        return breakfastincluded;
    }

    @Column(name = "PETSALLOWED")
    public int getPetsallowed() {
        return petsallowed;
    }

    @Column(name = "DATEAVAILABLEFROM")
    public Date getDateavaible() {
        return dateavaible;
    }

    @JoinColumn(name = "HOTEL_ID")
    @OneToOne(cascade = CascadeType.ALL)
    public Hotel getHotel() {
        return hotel;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public void setNumberofguest(int numberofguest) {
        this.numberofguest = numberofguest;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastincluded(int breakfastincluded) {
        this.breakfastincluded = breakfastincluded;
    }

    public void setPetsallowed(int petsallowed) {
        this.petsallowed = petsallowed;
    }

    public void setDateavaible(Date dateavaible) {
        this.dateavaible = dateavaible;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
