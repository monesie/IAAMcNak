package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.model.Answer;
import de.nordakademie.iaa.mcnak.model.Question;
import de.nordakademie.iaa.mcnak.service.AnswerService;
import de.nordakademie.iaa.mcnak.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class AnswerAction extends ActionSupport {

    /**
     * The answer service.
     */
    private AnswerService answerService;

    /**
     * The question service
     */
    private QuestionService questionService;

    /**
     * The answer currently edited.
     */
    private Answer answer;

    /**
     * The answerList for a Question.
     */
    private List<Answer> answerList;

    /**
     * The answer's identifier.
     */
    private Long answerId;

    /**
     * The question's identifier.
     */
    private Long questionId;

    /**
     * List of ClozeAnswers
     */
    private List<Answer> blankList;


    /**
     * Loads the answer with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String loadAnswer() {
        answer = answerService.loadAnswer(answerId);
        return answer.getQuestion().getQuestionType().toString();
    }

    /**
     * Loads the all answers for a question with the question identifier.
     *
     * @return the Struts outcome.
     */
    public String loadAllAnswerByQuestionId() {
        loadQuestionIdFromSession();
        answerList = answerService.loadAnswerByQuestionId(questionId);
        if (answerList.isEmpty()) {
            return "noEntry";
        } else {
            Question question = questionService.loadQuestion(questionId);
            return question.getQuestionType().toString();
        }
    }

    /**
     * Loads the questionId from the session and writes it into the local variable
     */
    private void loadQuestionIdFromSession() {
        Map session = ActionContext.getContext().getSession();
        if (session.get("currentQuestionId") != null && questionId == null) {
            questionId = (Long) session.get("currentQuestionId");
        }
    }

    /**
     * Deletes the questionId from the session
     *
     * @return SUCCESS
     */
    public String deleteQuestionIdFromSession() {
        Map session = ActionContext.getContext().getSession();
        session.remove("currentQuestionId");

        return SUCCESS;
    }

    /**
     * Saves the answer currently edited.
     *
     * @return the Struts outcome.
     */
    public String saveAnswer() {
        loadQuestionIdFromSession();
        Question question = questionService.loadQuestion(questionId);
        answer.setQuestion(question);
        answerService.saveAnswer(answer);
        return SUCCESS;
    }

    /**
     * Deletes the answer with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String deleteAnswer() {
        answerService.deleteAnswer(answerId);
        return SUCCESS;
    }

    /**
     * creates empty Strings in the blankList for the next form
     * changes the cloze's text and enters the blank identifier into th brackets
     *
     * @return SUCCESS
     */
    public String generateBlanks() {
        //ensure that the questionId is set from session
        loadQuestionIdFromSession();
        Question question = questionService.loadQuestion(questionId);

        String questionString = question.getText();
        Integer lastIndex = 0;
        Integer blankCounter = 0;
        while (lastIndex != -1) {
            lastIndex = questionString.indexOf("[]", lastIndex);
            if (lastIndex != -1) {
                blankCounter++;
                lastIndex += 2;
            }
        }

        blankList = new ArrayList<>();

        for (Integer i = 0; i < blankCounter; i++) {
            Answer answer = new Answer();
            answer.setBlank(i.toString());
            blankList.add(answer);

        }

        generateBlankIdInQuestionText(blankCounter, question);
        return SUCCESS;
    }

    /**
     * Is called by the method generateBlanks
     * Inserts the blank identifiers into the question text's brackets
     *
     * @param blankCounter
     * @param question
     */
    private void generateBlankIdInQuestionText(Integer blankCounter, Question question) {
        String questionText = question.getText();
        String newText = "";
        int start = 0;
        for (int i = 1; i <= blankCounter; i++) {
            int end = questionText.indexOf("[", start) + 1;
            newText += questionText.substring(start, end);
            newText += i;
            start = end;
        }
        newText += questionText.substring(start);
        question.setText(newText);
    }

    /**
     * reads answers from blankList and creates Answer objects
     *
     * @return SUCCESS
     */
    public String saveClozeAnswerList() {
        loadQuestionIdFromSession();
        for (int i = 1; i < blankList.size(); i++) {
            Question question = questionService.loadQuestion(questionId);
            Answer answer = blankList.get(i);
            answer.setQuestion(question);
            answer.setSolution(true);

            System.out.println(answer);
            answerService.saveAnswer(answer);
        }

        return SUCCESS;
    }

    public AnswerService getAnswerService() {
        return answerService;
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Answer> getBlankList() {
        return blankList;
    }

    public void setBlankList(List<Answer> blankList) {
        this.blankList = blankList;
    }

    public Long getDefaultAnswerId() {
        return answerList.get(0).getId();
    }
}
