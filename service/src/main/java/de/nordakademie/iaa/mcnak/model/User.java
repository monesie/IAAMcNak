package de.nordakademie.iaa.mcnak.model;

import de.nordakademie.iaa.mcnak.dataType.UserRole;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Simone Vonsien
 */

@Entity
public class User {

    /** The user's unique identifier */
    private String userName;

    /** The user's first name */
    private String firstName;

    /** The user's last name */
    private String lastName;

    /** The user's email */
    private String email;

    /** The century of the user */
    private String century;

    /** The user's role */
    private UserRole role;

    /** The examData the user created */
    private List<ExamData> examDataCreatedList;

    /** The exams the user created */
    private List<Exam> examCreatedList;

    /** the hashed password of the user */
    private String hashedPw;

    @Id
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    @Column(nullable = false)
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    public List<ExamData> getExamDataCreatedList() {
        return examDataCreatedList;
    }

    public void setExamDataCreatedList(List<ExamData> examDataCreatedList) {
        this.examDataCreatedList = examDataCreatedList;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "participant", orphanRemoval = true)
    public List<Exam> getExamCreatedList() { return examCreatedList;}

    public void setExamCreatedList(List<Exam> examCreatedList) {
        this.examCreatedList = examCreatedList;
    }

    public String getHashedPw() {
        return hashedPw;
    }

    public void setHashedPw(String hasehdPw) {
        this.hashedPw = hasehdPw;
    }
}
