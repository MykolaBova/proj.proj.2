package com.dropit.test;

import com.dropit.test.entity.Address;
import com.dropit.test.entity.Delivery;
import com.dropit.test.repository.AddressRepository;
import com.dropit.test.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		Address address = new Address("Khreshatik 3, Kiev, Ukraine");
		Delivery delivery = new Delivery();
		delivery.setAddress(address);
		address.addDelivery(delivery);
		addressRepository.save(address);
		deliveryRepository.save(delivery);
	}	
}
