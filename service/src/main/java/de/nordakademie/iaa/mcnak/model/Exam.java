package de.nordakademie.iaa.mcnak.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * The Exam entity
 *
 * @author Simone Vonsien
 * @author Marten Moraal
 */
@Entity
public class Exam {

    /**
     * The exam's identifier
     */
    private Long id;

    /**
     * The related participant (User)
     */
    private User participant;

    /**
     * The Date when the student has taken the exam
     */
    private Date startDate;

    /**
     * A flag that shows if the participant has passed the exam
     */
    private Boolean passed;

    /**
     * The related ExamData
     */
    private ExamData examData;

    /**
     * A List of given answers by the participant
     */
    private List<UserEntry> userEntryList;

    /**
     * The generates oneTimePasswort for the participant
     */
    private String oneTimePassword;

    /**
     * @return String The Exam as String
     * @author Nicolas Storl
     */
    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return examData.getCourseName() + " (" + dateFormat.format(examData.getLastCourseDay()) + "): "
                + examData.getExamName();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User user) {
        this.participant = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    @ManyToOne(optional = false)
    public ExamData getExamData() {
        return examData;
    }

    public void setExamData(ExamData examData) {
        this.examData = examData;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "exam", orphanRemoval = true)
    public List<UserEntry> getUserEntryList() {
        return userEntryList;
    }

    public void setUserEntryList(List<UserEntry> userEntryList) {
        this.userEntryList = userEntryList;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

}
