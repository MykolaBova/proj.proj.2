package com.dropit.test.repository;


import com.dropit.test.entity.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class PackageRepository {
    @Autowired
    EntityManager em;

    public Package findById(Long id) {
        return em.find(Package.class, id);
    }

    public Package save(Package pPackage) {

        if (pPackage.getId() == null) {
            em.persist(pPackage);
        } else {
            em.merge(pPackage);
        }

        return pPackage;
    }
}
