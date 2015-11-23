package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.model.Exam;
import de.nordakademie.iaa.mcnak.model.Question;
import de.nordakademie.iaa.mcnak.model.User;

import java.util.List;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public interface ExamService {

    /**
     * Loads the exam with the given id.
     *
     * @param id The identifier.
     * @return an exam or {@code null} if no matching exam was found.
     */
    Exam loadExam(Long id);


    /**
     * Loads and returns all open exam entities in the database for one student.
     *
     * @param participant
     * @return a list of open exam.
     */
    List<Exam> loadAllOpenExamByUser(User participant);

    /**
     * Loads and returns all solved exam entities in the database for one student.
     *
     * @param participant
     * @return a list of solved exam.
     */
    List<Exam> loadAllSolvedExamByUser(User participant);

    /**
     * Saves the given exam.
     *
     * @param exam The exam to be saved.
     */
    void saveExam(Exam exam);

    /**
     * Deletes the given exam.
     * @param exam The exam to be deleted.
     */
    //String deleteExam(Exam exam);

    /**
     * Deletes the exam with the given identifier.
     *
     * @param id The identifier.
     */
    String deleteExam(Long id);

    /**
     * Loads and returns the question set to an exam with the given identifier.
     *
     * @param id The identifier.
     */
    Question getFirstQuestionById(Long id);

    /**
     * Checks if the password is correct.
     *
     * @param examId          The identifier for the exam
     * @param enteredPassword The password to check
     * @return
     */
    Boolean validateOTP(Long examId, String enteredPassword);

    /**
     * Loads all participants for a given examDataId
     *
     * @param examDataId
     * @return List A List of User
     */

    List<User> loadAllParticipantsByExamDataId(Long examDataId);

    /**
     * This method sets the passed parameter in the exam-table
     *
     * @param examId
     */
    void rateExam(Long examId);

    List<Exam> loadExamListByExamDataId(Long examDataId);
}
