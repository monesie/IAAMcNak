package de.nordakademie.iaa.mcnak.dao;

import de.nordakademie.iaa.mcnak.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class QuestionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the question with the given id
     * @param id The unique question identifier
     * @return a question or {@code null} if there is no question existing with the given id
     */
    public Question load(Long id){
        return entityManager.find(Question.class, id);
    }



    /**
     * Returns all questions for an examData.
     *
     * @param examDataId the filter parameter
     * @return a list of questions.
     */
    @SuppressWarnings("unchecked")
    public List<Question> loadQuestionListByExamDataId(Long examDataId) {
        return entityManager.createQuery("select question from Question question " +
                "where question.examData.id = :examDataId").setParameter("examDataId",examDataId).getResultList();
    }

    /**
     * Saves the given question.
     *
     * @param question The question to be saved.
     */
    public void save(Question question) {
        if (question.getId() == null){
            entityManager.persist(question);
        } else {
            entityManager.merge(question);
        }
    }

    /**
     * Deletes the given question.
     * @param question The question to be deleted.
     */
    public void delete(Question question){
        entityManager.remove(question);
    }

}
