package com.dropit.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String address;

    @OneToMany(mappedBy="address")
    @JsonIgnore
    private List<Delivery> deliveries = new ArrayList<>();

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
    }

    public void removeReview(Delivery delivery) {
        this.deliveries.remove(delivery);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                '}';
    }
}
