package de.nordakademie.iaa.mcnak.dao;

import de.nordakademie.iaa.mcnak.model.Exam;
import de.nordakademie.iaa.mcnak.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class ExamDAO {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the exam with the given id
     *
     * @param id The unique identifier
     * @return an exam or {@code null} if there is no exam existing with the given id
     */
    public Exam load(Long id) {
        return entityManager.find(Exam.class, id);
    }

    /**
     * Returns all open exams for a student.
     *
     * @return a list of exams.
     */
    @SuppressWarnings("unchecked")
    public List<Exam> loadOpenByUser(User participant) {
        return entityManager.createQuery("select exam from Exam exam " +
                "where exam.participant = :participant and exam.startDate is null and exam.examData.status <> 0 " +
                "and exam.examData.startDate <= current_date ")
                .setParameter("participant", participant).getResultList();
    }

    /**
     * Returns all solved exams for a student.
     *
     * @param participant the filter parameter
     * @return a list of exams.
     */
    @SuppressWarnings("unchecked")
    public List<Exam> loadSolvedByUser(User participant) {
        return entityManager.createQuery("select exam from Exam exam " +
                "where exam.participant = :participant and exam.startDate is not null and exam.examData.status <> 0")
                .setParameter("participant", participant).getResultList();
    }

    /**
     * Saves the given exam.
     *
     * @param exam The exam to be saved.
     */
    public void save(Exam exam) {
        if (exam.getId() == null) {
            entityManager.persist(exam);
        } else {
            entityManager.merge(exam);
        }
    }

    /**
     * Deletes the given exam.
     *
     * @param exam The exam to be deleted.
     */
    public void delete(Exam exam) {
        entityManager.remove(exam);
    }

    /**
     * Returns all Participants for a given ExamDataId
     *
     * @param examDataId The identifier of the ExamData
     * @return a List of Users
     */
    @SuppressWarnings("unchecked")
    public List<User> loadAllParticipantsByExamDataId(Long examDataId) {

        return entityManager.createQuery("select distinct exam.participant from Exam exam " +
                "where exam.examData.id = :id").setParameter("id", examDataId).getResultList();
    }

    /**
     * Returns all Exams for a given ExamDataId
     *
     * @param examDataId identifier of ExamData
     * @return a List of Exams
     */
    @SuppressWarnings("unchecked")
    public List<Exam> loadExamListByExamDataId(Long examDataId) {
        return entityManager.createQuery("select exam from Exam exam " +
                "where exam.examData.id = :id and exam.startDate is null ").setParameter("id", examDataId).getResultList();
    }
}
