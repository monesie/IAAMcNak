package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.model.Answer;
import de.nordakademie.iaa.mcnak.model.UserEntry;

import java.util.List;

/**
 * Created by Nicolas on 21.11.2015.
 */
public interface AnswerService {

    /**
     * Loads the answer with the given id.
     * @param id The identifier.
     * @return an answer or {@code null} if no matching answer was found.
     */
    Answer loadAnswer(Long id);

    /**
     * Loads all answers for the given question ID
     * @param questionId
     * @return  a List of answers.
     */
    List<Answer> loadAnswerByQuestionId(Long questionId);


    /**
     * Saves the given answer.
     * @param answer The answer to be saved.
     */
    void saveAnswer(Answer answer);


    /**
     * Deletes the answer with the given identifier.
     * @param id The identifier.
     */
    void deleteAnswer(Long id);

}
