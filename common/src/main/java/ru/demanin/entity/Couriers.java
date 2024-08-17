package ru.demanin.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.demanin.status.CouriersStatus;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "couriers")
public class Couriers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "phone")
    private long phone;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CouriersStatus status;
    @Column(name = "coordinates")
    private String coordinates;
    @OneToMany
    private List<Order> orders;
}
