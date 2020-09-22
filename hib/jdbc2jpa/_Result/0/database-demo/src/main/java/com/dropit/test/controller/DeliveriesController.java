package com.dropit.test.controller;

import com.dropit.test.entity.Address;
import com.dropit.test.entity.Package;
import com.dropit.test.services.DeliveriesService;
import com.dropit.test.entity.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveriesController {

    @Autowired
    private DeliveriesService deliveriesService;

    @PostMapping("/deliveries")
    Delivery newDelivery(@RequestBody Address deliveryAddress) {

        Delivery delivery = deliveriesService.addDeliveryByAddress(deliveryAddress);

        return delivery;
    }

    @PostMapping("/packages/{id}")
    Delivery addPackage(@RequestBody Package pPackage, @PathVariable Long id) {

        Delivery delivery = deliveriesService.addPackageToDelivery(pPackage, id);

        return delivery;
    }

    @GetMapping("/deliveries")
    List<Delivery> allPackages() {

        List<Delivery> deliveries = deliveriesService.findAll();

        return deliveries;
    }

    @GetMapping("/deliveries/{id}")
    Delivery findById(@PathVariable Long id) {

        return deliveriesService.findById(id);
    }
}
