package com.bluethink.dev.service;

import com.bluethink.dev.enitity.User;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Singleton
public class UserServiceImpl implements UserService{

    private final EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @ReadOnly
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @TransactionalAdvice
    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;

    }

    @Override
    @TransactionalAdvice
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @ReadOnly
    public List<User> findAll() {
        return entityManager.
                createQuery("SELECT c FROM User c").
                getResultList();
    }

    @Override
    @Transactional
    @TransactionalAdvice
    public void update(Long id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        if (null != userToUpdate) {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmailId(user.getEmailId());
        } else {
            throw new RuntimeException("No such user available");
        }
    }
}
