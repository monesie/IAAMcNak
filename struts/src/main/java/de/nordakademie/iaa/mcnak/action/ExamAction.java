package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.dataType.ExamDataStatus;
import de.nordakademie.iaa.mcnak.dataType.UserRole;
import de.nordakademie.iaa.mcnak.model.Exam;
import de.nordakademie.iaa.mcnak.model.ExamData;
import de.nordakademie.iaa.mcnak.model.Question;
import de.nordakademie.iaa.mcnak.model.User;
import de.nordakademie.iaa.mcnak.service.ExamDataService;
import de.nordakademie.iaa.mcnak.service.ExamService;
import de.nordakademie.iaa.mcnak.service.UserService;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class ExamAction extends ActionSupport {
    /**
     * The exam service.
     */
    private ExamService examService;

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * The examData service.
     */
    private ExamDataService examDataService;

    /**
     * The students identifier
     */
    private String participant;

    /**
     * List of open exams.
     */
    private List<Exam> openExamList;

    /**
     * List of solved exams.
     */
    private List<Exam> solvedExamList;

    /**
     * List of questions
     */
    private List<Question> questionList;

    /**
     * String that stores temporary server responses
     */
    private String serverResponse;

    /**
     * the entered one time password
     */
    private String enteredPassword;

    /**
     * the examId
     */
    private Long examId;

    /**
     * The examData's identifier
     */
    private Long examDataId;

    /**
     * The exam currently edited
     */
    private Exam exam;

    /**
     * username of the user which should be used to create the exam
     */
    private String participantToAdd;

    /**
     * List of usernames
     */
    private List<String> possibleParticipantList;

    /**
     * List of Participants that have been added to the ExamData
     */
    private List<User> participantAddedList;

    /**
     * List of Exam by current ExamDataId
     */
    private List<Exam> examListByExamData;

    /**
     * fills the open ExamList and the solvedExamList for the given participant
     *
     * @return String SUCCESS or "noEntry" if no Entry found
     */
    public String loadAllExamForCurrentUser() throws Exception {
        participant = (String) ActionContext.getContext().getSession().get("username");
        openExamList = examService.loadAllOpenExamByUser(userService.loadUser(participant));
        solvedExamList = examService.loadAllSolvedExamByUser(userService.loadUser(participant));
        if (openExamList.isEmpty()) {
            return "noEntry";
        } else {
            return SUCCESS;
        }
    }

    /**
     * loads the Exams related to a given examDataId
     *
     * @return SUCCESS
     */
    public String loadExamListByExamDataId() {
        putExamDataIdIntoSession();
        examListByExamData = examService.loadExamListByExamDataId(examDataId);


        return SUCCESS;
    }

    /**
     * Loads the exam for the given examId
     *
     * @return SUCCESS
     */
    public String loadExam() {
        examService.loadExam(examId);
        return SUCCESS;
    }

    /**
     * deletes the Exam
     * in case of an error an ActionError is added.
     *
     * @return SUCCESS if exam was deleted
     */
    public String deleteExam() {
        String answer = examService.deleteExam(examId);
        if (answer.equals("noExam")) {
            addActionError(getText("error.invalidExam"));
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * rates the Exam with the given answers
     * sets exam.passed
     *
     * @return SUCCESS
     */
    public String rateExam() {

        examService.rateExam((Long) ActionContext.getContext().getSession().get("examId"));
        Map session = ActionContext.getContext().getSession();
        session.remove("examDataId");
        session.remove("examId");
        session.remove("question");
        return SUCCESS;
    }


    /**
     * validates the one time password of the exam
     *
     * @return String "right" or ERROR if the password is wrong
     */
    public String validateOTP() {
        Map session = ActionContext.getContext().getSession();
        Boolean passwordCorrect = examService.validateOTP(examId, enteredPassword);
        session.put("examId", examId);
        System.out.println(examDataId);
        exam = examService.loadExam(examId);
        session.put("examDataId", exam.getExamData().getId());
        if (passwordCorrect) {
            return "right";
        }
        addActionError("Your Password was incorrect.");
        return ERROR;
    }

    /**
     * starts the selected exam
     *
     * @return String questiontype or "tooLate" if the enddate is in teh past
     */
    public String startExam() {
        Map session = ActionContext.getContext().getSession();
        Long examId = (Long) session.get("examId");
        exam = examService.loadExam(examId);
        if ((new Date()).after(exam.getExamData().getEndDate())) {
            return "tooLate";
        }
        exam.setStartDate(new Date());
        examService.saveExam(exam);
        Question firstQuestion = examService.getFirstQuestionById(examId);
        session.put("question", firstQuestion);
        examDataId = exam.getExamData().getId();
        ExamData examData = examDataService.loadExamData(examDataId);
        if (examData.getStatus() != ExamDataStatus.examTaken)
            examData.setStatus(ExamDataStatus.examTaken);
        return firstQuestion.getQuestionType().toString();
    }

    /**
     * Loads the needed participant lists
     *
     * @return SUCCESS
     */
    public String prepareParticipantManagement() {
        putExamDataIdIntoSession();

        //load participantLists
        initializeParticipantManagement();
        return SUCCESS;
    }

    /**
     * Loads the needed participant lists
     */
    private void initializeParticipantManagement() {
        possibleParticipantList = userService.loadUserNameByRole(UserRole.Student);
        participantAddedList = examService.loadAllParticipantsByExamDataId(examDataId);
    }

    /**
     * puts the current ExamDataId into session or loads the entered examDataId
     */
    private void putExamDataIdIntoSession() {
        //load examDataId from session. If not available put current examDataId into session
        Map session = ActionContext.getContext().getSession();
        if (session.get("currentExamDataId") == null) {
            session.put("currentExamDataId", examDataId);
        } else {
            examDataId = (Long) session.get("currentExamDataId");
        }
    }

    /**
     * deletes the currentExamDataId from the session
     *
     * @return SUCCESS
     */
    public String deleteExamDataIdFromSession() {
        Map session = ActionContext.getContext().getSession();
        session.remove("currentExamDataId");
        return SUCCESS;
    }

    /**
     * creates an Exam entity for the stored user
     *
     * @return Strin SUCCESS or ERROR
     */
    public String createExam() {
        //Collect Data
        User user = userService.loadUser(participantToAdd);
        if (user == null) {
            addActionError(getText("error.invalidUserName"));
            return ERROR;
        } else {
            ExamData examData = examDataService.loadExamData(examDataId);

            //Write data to object
            exam.setParticipant(user);
            exam.setExamData(examData);
            exam.setOneTimePassword(generateOneTimePassword());

            //save object
            examService.saveExam(exam);
        }
        return SUCCESS;
    }

    /**
     * generates one time password from give information as hash
     *
     * @return the hashed password
     */
    private String generateOneTimePassword() {
        String stringToHash = participantToAdd + examDataId.toString() + new Date().toString();
        return DigestUtils.md5DigestAsHex(stringToHash.getBytes());
    }

    public List<Exam> getOpenExamList() {
        return openExamList;
    }

    public List<Exam> getSolvedExamList() {
        return solvedExamList;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public void setExamDataService(ExamDataService examDataService) {
        this.examDataService = examDataService;
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getExamDataId() {
        return examDataId;
    }

    public void setExamDataId(Long examDataId) {
        this.examDataId = examDataId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }


    public String getParticipantToAdd() {
        return participantToAdd;
    }

    public void setParticipantToAdd(String participantToAdd) {
        this.participantToAdd = participantToAdd;
    }

    public List<User> getParticipantAddedList() {
        if (participantAddedList == null) {
            initializeParticipantManagement();
        }
        return participantAddedList;
    }

    public void setParticipantAddedList(List<User> participantAddedList) {
        this.participantAddedList = participantAddedList;
    }

    public List<String> getPossibleParticipantList() {
        if (possibleParticipantList == null) {
            initializeParticipantManagement();
        }
        return possibleParticipantList;
    }

    public void setPossibleParticipantList(List<String> possibleParticipantList) {
        this.possibleParticipantList = possibleParticipantList;
    }

    public List<Exam> getExamListByExamData() {
        return examListByExamData;
    }

    public void setExamListByExamData(List<Exam> examListByExamData) {
        this.examListByExamData = examListByExamData;
    }

    /**
     * the getter is not related to a instance variable
     *
     * @return the first Exam from the openExamList
     */
    public Long getDefaultExamId() {
        return openExamList.get(0).getId();
    }
}
