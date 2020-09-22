package com.dropit.test.repository;

import com.dropit.test.entity.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Delivery findById(Long id) {
        return em.find(Delivery.class, id);
    }

    public List<Delivery> findAll() {
        Query query = em.createQuery("SELECT d FROM Delivery d");
        return (List<Delivery>) query.getResultList();
    }

    public Delivery save(Delivery delivery) {

        if (delivery.getId() == null) {
            em.persist(delivery);
        } else {
            em.merge(delivery);
        }

        return delivery;
    }
}
