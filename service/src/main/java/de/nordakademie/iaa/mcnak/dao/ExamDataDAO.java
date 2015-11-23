package de.nordakademie.iaa.mcnak.dao;


import de.nordakademie.iaa.mcnak.model.ExamData;
import de.nordakademie.iaa.mcnak.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class ExamDataDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the examData with the given id
     *
     * @param id The unique identifier
     * @return an examData or {@code null} if there is no examData existing with the given id
     */
    public ExamData load(Long id) {
        return entityManager.find(ExamData.class, id);
    }


    /**
     * Returns all examData to a given User.
     *
     * @param creator the filter parameter
     * @return a list of examData.
     */
    public List<ExamData> findAllByUser(User creator) {
        return entityManager.createQuery("select examData from ExamData examData " +
                "where examData.creator = :creator").setParameter("creator", creator).getResultList();
    }

    /**
     * Saves the given examData.
     *
     * @param examData examData to be saved.
     */
    public void save(ExamData examData) {
        if (examData.getId() == null) {
            entityManager.persist(examData);
        } else {
            entityManager.merge(examData);
        }
    }

    /**
     * Deletes the given examData.
     *
     * @param examData The examData to be deleted.
     */
    public void delete(ExamData examData) {
        entityManager.remove(examData);
    }

    /**
     * Updates the status of the given examData.
     *
     * @param examData The examData to be updated.
     */
    public void updateExamDataStatus(ExamData examData) {
        entityManager.merge(examData);
    }


}
