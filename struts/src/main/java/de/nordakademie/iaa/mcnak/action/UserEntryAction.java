package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.model.Answer;
import de.nordakademie.iaa.mcnak.model.Exam;
import de.nordakademie.iaa.mcnak.model.Question;
import de.nordakademie.iaa.mcnak.model.UserEntry;
import de.nordakademie.iaa.mcnak.service.AnswerService;
import de.nordakademie.iaa.mcnak.service.ExamService;
import de.nordakademie.iaa.mcnak.service.UserEntryService;

import java.util.List;
import java.util.Map;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class UserEntryAction extends ActionSupport {

    /**
     * The userEntry service.
     */
    private UserEntryService userEntryService;

    /**
     * The answer service
     */
    private AnswerService answerService;

    /**
     * The ExamService
     */
    private ExamService examService;

    /**
     * The userEntry currently edited.
     */
    private UserEntry userEntry;

    /**
     * The list of booleans
     */
    private List<Boolean> booleanList;

    /**
     * The userEntry's identifier.
     */
    private Long userEntryId;

    /**
     * The current question
     */
    private Question question;

    /**
     * The session
     */
    private Map session = ActionContext.getContext().getSession();

    private String[] resultList;

    private String resultElement;

    /**
     * List of ClozeAnswers
     */
    private List<String> blankList;

    public UserEntryAction() {
        Map session = ActionContext.getContext().getSession();
        this.question = (Question) session.get("question");
    }

    /**
     * Loads the userEntry with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String loadUserEntry() {
        userEntry = userEntryService.loadUserEntry(userEntryId);
        return SUCCESS;
    }

    /**
     * Saves the userEntry currently edited.
     *
     * @return the Struts outcome.
     */
    public String save() {
        question.getAnswerList().get(0);
        userEntryService.saveUserEntry(userEntry);
        return SUCCESS;
    }

    /**
     * Deletes the userEntry with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String deleteUserEntry() {
        userEntryService.deleteUserEntry(userEntryId);
        return SUCCESS;
    }

    public String initializeMultipleChoice() {
        return SUCCESS;
    }

    public String saveMultipleChoice() {
        for (String a : resultList) {

            Long answerId = Long.parseLong(a);
            Answer answer = answerService.loadAnswer(answerId);
            userEntry = new UserEntry();
            userEntry.setAnswer(answer);

            Long examId = (Long) session.get("examId");
            Exam exam = examService.loadExam(examId);
            userEntry.setExam(exam);

            userEntryService.saveUserEntry(userEntry);
        }
        //UserEntryService
        return SUCCESS;
    }

    public String initializeCloze() {
        return SUCCESS;
    }

    public String saveCloze() {
        int i = 0;
        for (int j = 1; j < blankList.size(); j++) {

            Question question = (Question) session.get("question");
            Answer answer = question.getAnswerList().get(i);
            userEntry = new UserEntry();
            userEntry.setAnswer(answer);

            Long examId = (Long) session.get("examId");
            Exam exam = examService.loadExam(examId);
            userEntry.setExam(exam);
            userEntry.setBlank(blankList.get(j));

            userEntryService.saveUserEntry(userEntry);
            i++;
        }

        return SUCCESS;
    }

    public String initialzeSingleChoice() {
        return SUCCESS;
    }

    public String saveSingleChoice() {
        System.out.println(resultElement);
        if (resultElement != null) {
            Long answerId = Long.parseLong(resultElement);
            Answer answer = answerService.loadAnswer(answerId);
            userEntry = new UserEntry();
            userEntry.setAnswer(answer);

            Long examId = (Long) session.get("examId");
            Exam exam = examService.loadExam(examId);
            userEntry.setExam(exam);

            System.out.println(userEntry);

            userEntryService.saveUserEntry(userEntry);
        }
        return SUCCESS;
    }

    public List<Answer> getAnswerList() {
        List<Answer> answerList = question.getAnswerList();
        return answerList;
    }

    public void setUserEntryService(UserEntryService userEntryService) {
        this.userEntryService = userEntryService;
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public UserEntryService getUserEntryService() {
        return userEntryService;
    }

    public void setUserEntry(UserEntry userEntry) {
        this.userEntry = userEntry;
    }

    public Long getUserEntryId() {
        return userEntryId;
    }

    public void setUserEntryId(Long userEntryId) {
        this.userEntryId = userEntryId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public UserEntry getUserEntry() {
        return userEntry;
    }

    public List<Boolean> getBooleanList() {
        return booleanList;
    }

    public void setBooleanList(List<Boolean> booleanList) {
        this.booleanList = booleanList;
    }


    public String[] getResultList() {
        return resultList;
    }

    public void setResultList(String[] resultList) {
        this.resultList = resultList;
    }

    public String getResultElement() {
        return resultElement;
    }

    public void setResultElement(String resultElement) {
        this.resultElement = resultElement;
    }

    public List<String> getBlankList() {
        return blankList;
    }

    public void setBlankList(List<String> blankList) {
        this.blankList = blankList;
    }
}