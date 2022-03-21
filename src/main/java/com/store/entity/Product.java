package com.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String image;

    @Column(length = 3000, name = "description")
    private String description;

    @Column(name = "CPU")
    private String CPU;

    @Column(name = "RAM")
    private String RAM;

    @Column(name = "weight")
    private String weight;

    @Column(name = "system")
    private String system;

    @Column(name = "diagonal")
    private String diagonal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    private List<User> userList;

}
