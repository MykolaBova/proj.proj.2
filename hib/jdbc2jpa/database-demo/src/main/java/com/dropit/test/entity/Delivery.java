package com.dropit.test.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Address address;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "delivery")
    //@JsonIgnore
    private List<Package> packages = new ArrayList<>();

    public Delivery() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void addPackage(Package pPackage) {
        this.packages.add(pPackage);
    }

    public void removePackages(Package pPackage) {
        this.packages.remove(pPackage);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                '}';
    }
}
