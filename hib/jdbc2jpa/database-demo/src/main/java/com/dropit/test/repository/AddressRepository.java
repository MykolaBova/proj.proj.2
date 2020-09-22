package com.dropit.test.repository;

import com.dropit.test.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class AddressRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Address save(Address address) {

        if (address.getId() == null) {
            em.persist(address);
        } else {
            em.merge(address);
        }

        return address;
    }
}
