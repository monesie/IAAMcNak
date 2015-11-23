package de.nordakademie.iaa.mcnak.dao;

import de.nordakademie.iaa.mcnak.model.Answer;
import de.nordakademie.iaa.mcnak.model.UserEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Nicolas Storl
 * @author Simone Vonsien
 */
public class AnswerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the answer with the given id
     *
     * @param id The unique answer identifier
     * @return an answer or {@code null} if there is no answer existing with the given id
     */
    public Answer load(Long id) {
        return entityManager.find(Answer.class, id);
    }

    /**
     * Returns all answers.
     *
     * @param questionId The answer's identifier
     * @return a list of answers.
     */
    @SuppressWarnings("unchecked")
    public List<Answer> findAllByQuestionId(Long questionId) {
        return entityManager.createQuery("select answer from Answer answer where answer.question.id = :questionId")
                .setParameter("questionId", questionId).getResultList();
    }

    /**
     * Saves the given answers.
     *
     * @param answer The answer to be saved.
     */
    public void save(Answer answer) {
        if (answer.getId() == null) {
            entityManager.persist(answer);
        } else {
            entityManager.merge(answer);
        }
    }

    /**
     * Deletes the given answer.
     *
     * @param answer The answer to be deleted.
     */
    public void delete(Answer answer) {
        entityManager.remove(answer);
    }

}
