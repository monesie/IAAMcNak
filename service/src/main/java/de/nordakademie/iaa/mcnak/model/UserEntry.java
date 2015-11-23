package de.nordakademie.iaa.mcnak.model;

import javax.persistence.*;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntry {

    /** the unique identifier */
    private Long id;

    /** the related exam */
    private Exam exam;

    /** the related answer */
    private Answer answer;

    /** the blank text the user entered */
    private String blank;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @ManyToOne(optional = false)
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }
}
