package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.dataType.QuestionType;
import de.nordakademie.iaa.mcnak.model.Answer;
import de.nordakademie.iaa.mcnak.model.Exam;
import de.nordakademie.iaa.mcnak.model.Question;
import de.nordakademie.iaa.mcnak.service.ExamDataService;
import de.nordakademie.iaa.mcnak.service.ExamService;
import de.nordakademie.iaa.mcnak.service.QuestionService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class QuestionAction extends ActionSupport {

    /**
     * The question service.
     */
    private QuestionService questionService;

    /**
     * The examData service.
     */
    private ExamDataService examDataService;

    /**
     * The exam service
     */
    private ExamService examService;

    /**
     * The question currently edited.
     */
    private Question question;

    /**
     * The question currently edited.
     */
    private List<Question> questionList;

    /**
     * The question's identifier.
     */
    private Long questionId;


    /**
     * The current session
     */
    private Map session = ActionContext.getContext().getSession();

    /**
     * The examData's identifier
     */
    private Long examDataId;

    /**
     * The Answers to current question
     */
    private List<Answer> answerList;

    /**
     * Loads the question with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String loadQuestion() {
        question = questionService.loadQuestion(questionId);
        return question.getQuestionType().toString();
    }

    /**
     * Fills the questionList with the questions of the ExamDataId in the session
     *
     * @return SUCCESS or "noEntry" if no entry was found
     */
    public String loadQuestionListByExamDataId() {
        if (session.get("currentExamDataId") == null) {
            session.put("currentExamDataId", examDataId);
        } else {
            examDataId = (Long) session.get("currentExamDataId");
        }

        questionList = questionService.loadQuestionListByExamDataId(examDataId);
        if (questionList.isEmpty()) {
            return "noEntry";
        } else {
            return SUCCESS;
        }
    }

    /**
     * Saves the question currently edited.
     *
     * @return the Struts outcome.
     */
    public String saveQuestion() {
        loadExamDataIdFromSession();
        question.setExamData(examDataService.loadExamData(examDataId));
        questionService.saveQuestion(question);
        questionId = question.getId();
        putCurrentQuestionIdIntoSession();
        return SUCCESS;
    }

    /**
     * sets Questiontype, puts the id in the session an saves the question
     *
     * @return SUCCESS
     */
    public String saveClozeQuestion() {
        question.setQuestionType(QuestionType.Cloze);
        saveQuestion();
        questionId = question.getId();
        putCurrentQuestionIdIntoSession();
        return SUCCESS;
    }

    /**
     * Deletes the question with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String deleteQuestion() {
        questionService.deleteQuestion(questionId);
        return SUCCESS;
    }

    /**
     * Loads the question from the session
     *
     * @return success
     */
    public String loadCurrentQuestion() {
        question = (Question) session.get("question");
        return SUCCESS;
    }


    /**
     * save the current questionId into the session
     *
     * @return SUCCESS or ERROR
     */
    public String putCurrentQuestionIdIntoSession() {
        Map session = ActionContext.getContext().getSession();
        if (questionId != null) {
            session.put("currentQuestionId", questionId);
            return SUCCESS;
        } else {
            addActionError(getText("error.noQuestionSelected"));
            return ERROR;
        }
    }

    /***
     * deletes the ExamDataId from the session
     *
     * @return SUCCESS
     */
    public String deleteCurrentExamDataIdFromSession() {
        Map session = ActionContext.getContext().getSession();
        session.remove("currentExamDataId");
        return SUCCESS;
    }


    /**
     * deletes the questionId from the session
     *
     * @return SUCCESS
     */
    public String deleteCurrentQuestionIdFromSession() {
        Map session = ActionContext.getContext().getSession();
        session.remove("currentQuestionId");
        return SUCCESS;
    }

    /**
     * loads the Next Question if available
     *
     * @return SUCCESS or ERROR if the time is over
     */
    public String loadNextQuestion() {
        Question currentQuestion = (Question) session.get("question");
        Long examDataId = (Long) session.get("examDataId");

        question = questionService.loadNextQuestion(currentQuestion, examDataId);
        if (question == null) {
            session.remove("question");
            return SUCCESS;
        }

        Long duration = currentQuestion.getExamData().getDuration();
        Exam exam = examService.loadExam((Long) session.get("examId"));
        Date start = exam.getStartDate();
        Date now = new Date();
        Boolean timeOver = (now.getTime() - start.getTime()) > (duration * 60000);

        if (timeOver) {
            session.remove("question");
            addActionMessage(getText("msg.timeOver"));
            return ERROR;
        }
        session.put("question", question);
        return question.getQuestionType().toString();
    }

    /**
     * loads the examDataId from the session
     *
     * @return
     */
    public String loadExamDataIdFromSession() {
        examDataId = (Long) session.get("currentExamDataId");
        return SUCCESS;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void setExamDataService(ExamDataService examDataService) {
        this.examDataService = examDataService;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getExamDataId() {
        return examDataId;
    }

    public void setExamDataId(Long examDataId) {
        this.examDataId = examDataId;
    }

    public Long getDefaultQuestionId() {
        return questionList.get(0).getId();
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }
}
