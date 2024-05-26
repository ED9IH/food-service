package ru.demanin.entity;



import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "orders")
public class Order {
    public Order(String status, Date timeStamp, Restaurant restaurants, Customer customers, Couriers couriers) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.restaurants = restaurants;
        this.customers = customers;
        this.couriers = couriers;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "status")
    private String status;
    @Column(name = "timestamp")
    private Date timeStamp;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurants;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customers;
    @ManyToOne
    @JoinColumn(name = "courier_id",referencedColumnName = "id")
    private Couriers couriers;

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Restaurant getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurant restaurants) {
        this.restaurants = restaurants;
    }

    public Customer getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customers) {
        this.customers = customers;
    }

    public Couriers getCouriers() {
        return couriers;
    }

    public void setCouriers(Couriers couriers) {
        this.couriers = couriers;
    }
}
