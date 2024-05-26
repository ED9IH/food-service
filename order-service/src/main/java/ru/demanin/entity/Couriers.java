package ru.demanin.entity;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "couriers")
public class Couriers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "phone")
    private long phone;
    @Column(name = "status")
    private String status;
    @Column(name = "coordinates")
    private String coordinates;
    @OneToMany
    private List<Order> orders;
}
