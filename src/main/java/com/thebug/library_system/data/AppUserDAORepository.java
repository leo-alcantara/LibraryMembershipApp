package com.thebug.library_system.data;

import com.thebug.library_system.model.AppUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class AppUserDAORepository implements AppUserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class, id);
    }

    @Override
    @Transactional
    public Collection<AppUser> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM AppUser a", AppUser.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(id);

    }
}
