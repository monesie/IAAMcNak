package de.nordakademie.iaa.mcnak.model;

import de.nordakademie.iaa.mcnak.dataType.EvaluationRule;
import de.nordakademie.iaa.mcnak.dataType.ExamDataStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */

@Entity
public class ExamData {

    /**
     * The ExamData's identifier
     */
    private Long id;

    /**
     * The creator of the ExamData
     */
    private User creator;

    /**
     * The name of the ExamData
     */
    private String examName;

    /**
     * The course name the examData was made for
     */
    private String courseName;

    /**
     * The last day the course was given
     */
    private Date lastCourseDay;

    /**
     * The date when the exam should be available for students
     */
    private Date startDate;

    /**
     * The last day to do the exam
     */
    private Date endDate;

    /**
     * The dureation of the exam in minutes
     */
    private Long duration;

    /**
     * reachable credit points
     */
    private Float creditPoint;

    /**
     * the needed percetage of credit points to pass the exam
     */
    private Float passLimit;

    /**
     * the evaluation rule of the exam
     * fullpoint = all correct answer have to be checked for creditpoints
     * partialpoints = every correct answer counts
     */
    private EvaluationRule evaluationRule;

    /**
     * a flag that determines if minus points are given for wrong answers
     */
    private Boolean minusPoints;

    /**
     * The related questions
     */
    private List<Question> questionList;

    /**
     * The status of the exam
     * In progress: The teacher is working on the examData
     * Released: The teacher has finished the examData and participants can start an exam
     * examTaken: A student has taken an exam. The teacher can't change the examData
     */
    private ExamDataStatus status;

    /**
     * The related exams for this ExamData
     */
    private List<Exam> examList;

    /**
     * @return String The ExamData's representation as String
     * @author Nicolas Storl
     */
    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return courseName + " (" + dateFormat.format(lastCourseDay) + "): " + examName + " [" + status + "]";
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @Column(nullable = false)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(nullable = false)
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Column(nullable = false)
    public Float getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(Float creditPoint) {
        this.creditPoint = creditPoint;
    }

    @Column(nullable = false)
    public Float getPassLimit() {
        return passLimit;
    }

    public void setPassLimit(Float passLimit) {
        this.passLimit = passLimit;
    }

    @Column(nullable = false)
    public EvaluationRule getEvaluationRule() {
        return evaluationRule;
    }

    public void setEvaluationRule(EvaluationRule evaluationRule) {
        this.evaluationRule = evaluationRule;
    }

    @Column(nullable = false)
    public Boolean getMinusPoints() {
        return minusPoints;
    }

    public void setMinusPoints(Boolean minusPoints) {
        this.minusPoints = minusPoints;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "examData", orphanRemoval = true)
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Column(nullable = false)
    public Date getLastCourseDay() {
        return lastCourseDay;
    }

    public void setLastCourseDay(Date lastCourseDay) {
        this.lastCourseDay = lastCourseDay;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "examData", orphanRemoval = true)
    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    @ManyToOne(optional = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Column(nullable = false)
    public ExamDataStatus getStatus() {
        return status;
    }

    public void setStatus(ExamDataStatus status) {
        this.status = status;
    }
}
