package de.nordakademie.iaa.mcnak.dao;

import de.nordakademie.iaa.mcnak.dataType.UserRole;
import de.nordakademie.iaa.mcnak.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JPA-based data access object for the user entity.
 *
 * @author Simone Vonsien
 */
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the User with the given username
     *
     * @param username The unique username
     * @return a user or {@code null} if the is no user existing with the given username
     */
    public User load(String username) {
        return entityManager.find(User.class, username);
    }

    /**
     * Returns all users.
     *
     * @return a list of users.
     */
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return entityManager.createQuery("select user from User user").getResultList();
    }

    /**
     * Saves the given user.
     *
     * @param user The user to be saved.
     */
    public void save(User user) {
        if (this.load(user.getUserName()) == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    /**
     * Deletes the given user.
     *
     * @param user The user to be deleted.
     */
    public void delete(User user) {
        entityManager.remove(user);
    }

    public List<User> loadUserListByRole(UserRole role) {
        return entityManager.createQuery("select user from User user" +
                " where user.role = :role").setParameter("role", role).getResultList();
    }
}
