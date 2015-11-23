package de.nordakademie.iaa.mcnak.dao;

import de.nordakademie.iaa.mcnak.model.UserEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class UserEntryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the userEntry with the given id
     * @param id The unique userEntry
     * @return a userEntry or {@code null} if there is no userEntry existing with the given id
     */
    public UserEntry load(Long id){
        return entityManager.find(UserEntry.class, id);
    }

    /**
     * Saves the given userEntry.
     *
     * @param userEntry The userEntry to be saved.
     */
    public void save(UserEntry userEntry) {
        if (userEntry.getId() == null){
            entityManager.persist(userEntry);
        } else {
            entityManager.merge(userEntry);
        }
    }

    /**
     * Deletes the given userEntry.
     * @param userEntry The userEntry to be deleted.
     */
    public void delete(UserEntry userEntry){
        entityManager.remove(userEntry);
    }

    /**
     * Returns the user entry if it was found
     * @param answerId the answer's identifier
     * @param examId the exam's identifier
     * @return UserEntry or @null if no entry was found
     */
    public UserEntry findUserEntryByAnswerId(Long answerId, Long examId) {
        List<UserEntry> userEntryList = entityManager.createQuery("select userEntry from UserEntry userEntry " +
                "where userEntry.answer.id = :answerId and userEntry.exam.id = :examId")
                .setParameter("answerId", answerId).setParameter("examId", examId).getResultList();
        if (userEntryList.size() != 0){
            return userEntryList.get(0);
        }
        return null;
    }

}
