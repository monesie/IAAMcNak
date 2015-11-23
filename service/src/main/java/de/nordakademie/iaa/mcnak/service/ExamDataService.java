package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.model.ExamData;
import de.nordakademie.iaa.mcnak.model.User;

import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public interface ExamDataService {

    /**
     * Loads the examData with the given id.
     * @param id The identifier.
     * @return a examData or {@code null} if no matching examData was found.
     */
    ExamData loadExamData(Long id);

    /**
     * Loads and returns all examData entities in the database for a teacher.7
     * @param creator The identifier
     * @return a list of examData or {@code null} if no matching examData was found.
     */
    List<ExamData> loadAllExamDataByUser(User creator);

    /**
     * Saves the given examData.
     * @param examData The examData to be saved.
     */
    void saveExamData(ExamData examData);

    /**
     * Deletes the examData with the given identifier.
     * @param id The identifier.
     */
    void deleteExamData(Long id);

    /**
     * Updates the examData with the given identifier.
     * @param examData The identifier.
     */
    void updateExamDataStatus(ExamData examData);


}
