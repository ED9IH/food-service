package ru.demanin.entity;



import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "phone")
    private int phone;
    @Column(name = "email")

    private String email;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
}
