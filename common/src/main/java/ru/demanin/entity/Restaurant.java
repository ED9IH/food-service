package ru.demanin.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.demanin.status.RestaurantStatus;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RestaurantStatus restaurantStatus;
    @Column(name = "name")
    private String name;
    @Column(name = "coordinates")
    private String coordinates;
    @OneToMany(mappedBy = "restaurants")
    private List<Order> orders;
    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantMenuItems> restaurantMenuItems;
}
