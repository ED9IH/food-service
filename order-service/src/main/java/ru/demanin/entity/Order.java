package ru.demanin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor

@Getter
@Setter
public class Order {
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

}
