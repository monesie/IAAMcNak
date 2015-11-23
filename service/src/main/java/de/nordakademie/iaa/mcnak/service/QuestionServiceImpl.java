package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dao.QuestionDAO;
import de.nordakademie.iaa.mcnak.model.Question;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class QuestionServiceImpl implements QuestionService {

    QuestionDAO questionDAO;

    @Override
    public Question loadQuestion(Long id) {
        return questionDAO.load(id);
    }

    @Override
    public Question loadNextQuestion(Question currentQuestion, Long examDataId) {
        List<Question> questionList = loadQuestionListByExamDataId(examDataId);
        int nextIndex = questionList.indexOf(currentQuestion) + 1;

        if (nextIndex >= questionList.size()) {
            return null;
        }
        Question nextQuestion = questionList.get(nextIndex);

        return nextQuestion;
    }

    @Override
    public List<Question> loadQuestionListByExamDataId(Long examDataId) {
        return questionDAO.loadQuestionListByExamDataId(examDataId);
    }

    @Override
    public void saveQuestion(Question question) {
        questionDAO.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionDAO.load(id);

        if (question != null) {
            questionDAO.delete(question);
        }
    }

    @Inject
    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
}
