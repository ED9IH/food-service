package ru.demanin.entity;



import javax.persistence.*;

@Entity

@Table(name = "restaurant_menu_items")
public class RestaurantMenuItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private long price;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id")
    private Restaurant restaurant;
}
