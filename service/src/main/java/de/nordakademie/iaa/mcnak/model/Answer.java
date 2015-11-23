package de.nordakademie.iaa.mcnak.model;

import de.nordakademie.iaa.mcnak.dataType.QuestionType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Answer Entity
 *
 * @author Nicolas Storl
 * @author Simone Vonsien
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Answer {

    /**
     * The Answer's unique identifier
     */
    private Long id;

    /**
     * The Answer's text
     */
    private String text;

    /**
     * The related question
     */
    private Question question;

    /**
     * Flag if the answer is the solution
     */
    private boolean solution;

    /**
     * The cloze's blank number
     */
    private String blank;

    /**
     * @return String The answer as String
     * @author Nicolas Storl
     */
    @Override
    public String toString() {
        if ("".equals(blank) || blank == null) {
            String string = text;
            if (solution) {
                string = " [X]" + string;
            } else {
                string = " [  ]" + string;
            }
            return string;
        } else {
            return text;
        }
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
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(optional = false)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

}
