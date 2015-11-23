package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.dataType.ExamDataStatus;
import de.nordakademie.iaa.mcnak.model.ExamData;
import de.nordakademie.iaa.mcnak.model.User;
import de.nordakademie.iaa.mcnak.service.ExamDataService;
import de.nordakademie.iaa.mcnak.service.UserService;

import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class ExamDataAction extends ActionSupport {

    /**
     * The examData service.
     */
    private ExamDataService examDataService;

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * The examData currently edited.
     */
    private ExamData examData;

    /**
     * List of all examData for a teacher.
     */
    private List<ExamData> examDataList;

    /**
     * The user currently editing.
     */
    private User user;

    /**
     * The current username
     */
    private String userName;

    /**
     * The exam data's identifier.
     */
    private Long examDataId;

    /**
     * Loads the exam data with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String loadExamData() {
        examData = examDataService.loadExamData(examDataId);
        return SUCCESS;
    }

    /**
     * this method gives all of the ExamData for a user
     *
     * @return SUCCESS
     */
    public String showAllExamDataByCreator(){

        userName = (String) ActionContext.getContext().getSession().get("username");
        user = userService.loadUser(userName);
        examDataList = examDataService.loadAllExamDataByUser(user);
        if (examDataList.isEmpty()) {
            return "noEntry";
        } else {
            return SUCCESS;
        }
    }

    /**
     * Sets the examDatas status on "released"
     * @return SUCCES or ERROR
     */
    public String examDataReady() {
        examData = examDataService.loadExamData(examDataId);
        if (examData.getStatus().equals(ExamDataStatus.inProgress)) {
            if (examData.getQuestionList().isEmpty()) {
                addActionError(getText("error.errorNoQuestions"));
                return ERROR;
            } else {
                examData.setStatus(ExamDataStatus.released);
                examDataService.updateExamDataStatus(examData);
                return SUCCESS;
            }
        } else {
            addActionError(getText("error.errorRelease"));
            return ERROR;
        }
    }

    /**
     * Saves the examData currently edited.
     *
     * @return the Struts outcome.
     */
    public String saveExamData() {
        if (examData.getStatus() == null || "".equals(examData.getStatus())) {
            examData.setStatus(ExamDataStatus.inProgress);
            String creatorName = (String) ActionContext.getContext().getSession().get("username");
            user = userService.loadUser(creatorName);
            examData.setCreator(user);
            if (examData.getEndDate().before(examData.getStartDate())) {
                addActionError(getText("error.endBeforeStart"));
                return ERROR;
            } if (examData.getStartDate().before(examData.getLastCourseDay())) {
                addActionError(getText("error.startBeforeLastCourseDay"));
                return ERROR;
            }
            else {
                examDataService.saveExamData(examData);
                return SUCCESS;
            }
        }
        if (examData.getStatus().equals(ExamDataStatus.examTaken)) {
            addActionError(getText("error.examTaken"));
            return ERROR;
        } else {
            String creatorName = (String) ActionContext.getContext().getSession().get("username");
            user = userService.loadUser(creatorName);
            examData.setCreator(user);
            examDataService.saveExamData(examData);
            return SUCCESS;
        }
    }

    /**
     * Deletes the examData with the current identifier.
     *
     * @return the Struts outcome.
     */
    public String deleteExamData() {
        examDataService.deleteExamData(examDataId);
        return SUCCESS;
    }

    public void setExamDataService(ExamDataService examDataService) {
        this.examDataService = examDataService;
    }

    public ExamData getExamData() {
        return examData;
    }

    public void setExamData(ExamData examData) {
        this.examData = examData;
    }

    public List<ExamData> getExamDataList() {
        return examDataList;
    }

    public void setExamDataList(List<ExamData> examDataList) {
        this.examDataList = examDataList;
    }

    public Long getExamDataId() {
        return examDataId;
    }

    public void setExamDataId(Long examDataId) {
        this.examDataId = examDataId;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDefaultExamDataId() {
        return examDataList.get(0).getId();
    }
}
