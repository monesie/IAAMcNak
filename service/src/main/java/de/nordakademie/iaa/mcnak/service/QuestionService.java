package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.model.Question;

import java.util.List;

/**
 * Public interface of the question service.
 * @author Sonja Scholz
 * @author Nicolas Storl
 */

public interface QuestionService {

    /**
     * Loads the question with the given id.
     * @param id The identifier.
     * @return a question or {@code null} if no matching question was found.
     */
    Question loadQuestion(Long id);

    /**
     * The methods returns the following question
     * @author Marten Moraal
     * @param question, examDataId
     * @return question The following question for examData
     */
    Question loadNextQuestion(Question question, Long examDataId);

    /**
     * Loads and returns all question entities in the database for a given examData.
     * @return a list of questions.
     * @param examDataId the identifier of the examData
     */
    List<Question> loadQuestionListByExamDataId(Long examDataId);

    /**
     * Saves the given question.
     * @param question The question to be saved.
     */
    void saveQuestion(Question question);

    /**
     * Deletes the question with the given identifier.
     * @param id The identifier.
     */
    void deleteQuestion(Long id);
}
