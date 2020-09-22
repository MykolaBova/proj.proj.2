package com.dropit.test.services;

import com.dropit.test.entity.Address;
import com.dropit.test.entity.Delivery;
import com.dropit.test.entity.Package;
import com.dropit.test.repository.AddressRepository;
import com.dropit.test.repository.DeliveryRepository;
import com.dropit.test.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveriesService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private PackageRepository packageRepository;

    public Delivery addDeliveryByAddress(Address deliveryAddress) {

        Delivery delivery = new Delivery();
        delivery.setAddress(deliveryAddress);
        deliveryAddress.addDelivery(delivery);

        addressRepository.save(deliveryAddress);
        deliveryRepository.save(delivery);

        return delivery;
    }

    public Delivery addPackageToDelivery(Package pPackage, Long id) {

        Delivery delivery = deliveryRepository.findById(id);

        delivery.addPackage(pPackage);
        pPackage.setDelivery(delivery);

        packageRepository.save(pPackage);
        deliveryRepository.save(delivery);

        return delivery;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(Long id) {
        return deliveryRepository.findById(id);
    }
}
