package com.veridic.hackathon.evstations.model;



import javax.persistence.*;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Station(Long id, String name, String address, Long price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public Station() {
        super();
    }

    private String address;




    @Column(scale=2)
    private double price;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
