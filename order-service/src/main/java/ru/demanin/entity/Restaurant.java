package ru.demanin.entity;



import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "coordinates")
    private String coordinates;
    @OneToMany(mappedBy = "restaurants")
    private List<Order> orders;
    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantMenuItems> restaurantMenuItems;
}
