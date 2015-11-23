package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dao.AnswerDAO;
import de.nordakademie.iaa.mcnak.model.Answer;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Nicolas on 21.11.2015.
 */
public class AnswerServiceImpl implements AnswerService {

    AnswerDAO answerDAO;

    @Override
    public Answer loadAnswer(Long id) {
        return answerDAO.load(id);
    }

    @Override
    public List<Answer> loadAnswerByQuestionId(Long questionId) {
        return answerDAO.findAllByQuestionId(questionId);
    }


    @Override
    public void saveAnswer(Answer answer) {
        answerDAO.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        Answer answer = answerDAO.load(id);

        if (answer != null) {
            answerDAO.delete(answer);
        }
    }

    @Inject
    public void setAnswerDAO(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }
}
