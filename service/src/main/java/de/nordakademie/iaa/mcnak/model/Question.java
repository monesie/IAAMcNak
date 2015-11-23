package de.nordakademie.iaa.mcnak.model;

import de.nordakademie.iaa.mcnak.dataType.QuestionType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {

    /**
     * The question's unique identifier
     */
    private Long id;

    /**
     * The related examData
     */
    private ExamData examData;

    /**
     * The question's type
     */
    private QuestionType questionType;

    /**
     * The question text
     */
    private String text;

    /**
     * The List of answers related to the quesiton
     */
    private List<Answer> answerList;

    /**
     * The reachable points for this question
     */
    private int points;

    /**
     * @return String The question as String
     */
    @Override
    public String toString() {
        return questionType + ": " + text + " (" + points + ")";
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
    public ExamData getExamData() {
        return examData;
    }

    public void setExamData(ExamData examData) {
        this.examData = examData;
    }

    @Column(nullable = false)
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Column(nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "question", orphanRemoval = true)
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (!getId().equals(question.getId())) return false;
        return getText().equals(question.getText());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getText().hashCode();
        return result;
    }
}
