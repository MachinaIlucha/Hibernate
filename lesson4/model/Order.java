package hibernate.lesson4.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    private long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    public Order() {
    }

    @Id
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    @Column(name = "ORDER_ID")
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUserOrdered() {
        return userOrdered;
    }

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    public Room getRoom() {
        return room;
    }

    @Column(name = "DATEFROM")
    public Date getDateFrom() {
        return dateFrom;
    }

    @Column(name = "DATETO")
    public Date getDateTo() {
        return dateTo;
    }

    @Column(name = "MONEYPAID")
    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserOrdered(User userOrdered) {
        this.userOrdered = userOrdered;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    @Override
    public String toString() {
        DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        String strDateFrom = dateformat.format(dateFrom);
        String strDateTo = dateformat.format(dateTo);

        return "" +
                "" + id +
                ", " + userOrdered +
                ", " + room +
                ", " + strDateFrom +
                ", " + strDateTo +
                ", " + moneyPaid;
    }
}
