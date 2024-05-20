package ru.demanin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
@NoArgsConstructor
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
}
