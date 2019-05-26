package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOTELS")
public class Hotel {

    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Room> rooms;

    public Hotel() {
    }

    @Id
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    @Column(name = "HOTEL_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAMEOFHOTEL")
    public String getName() {
        return name;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL, mappedBy = "HOTEL")
    public List<Room> getRooms() {
        return rooms;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "" +
                "" + id +
                ", " + name +
                ", " + country +
                ", " + city +
                ", " + street +
                ", " + rooms;
    }
}
