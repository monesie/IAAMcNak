package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dao.ExamDAO;
import de.nordakademie.iaa.mcnak.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class ExamServiceImpl implements ExamService {

    ExamDAO examDAO;

    /**
     * The userEntry service.
     */
    private UserEntryService userEntryService;

    @Override
    public Exam loadExam(Long id) {
        return examDAO.load(id);
    }

    @Override
    public List<Exam> loadAllOpenExamByUser(User participant) {
        return examDAO.loadOpenByUser(participant);
    }

    ;

    @Override
    public List<Exam> loadAllSolvedExamByUser(User participant) {
        return examDAO.loadSolvedByUser(participant);
    }

    ;

    @Override
    public void saveExam(Exam exam) {
        examDAO.save(exam);
    }

    /*@Override
    public String deleteExam(Exam exam) {
        examDAO.delete(exam);
    }*/

    @Override
    public String deleteExam(Long id) {
        Exam exam = examDAO.load(id);

        if (exam != null) {
            examDAO.delete(exam);
            return "success";
        }
        return "noExam";
    }

    @Override
    public Question getFirstQuestionById(Long id) {
        Exam exam = loadExam(id);
        return exam.getExamData().getQuestionList().get(0);
    }

    @Override
    public Boolean validateOTP(Long examId, String enteredPassword) {
        Exam exam = examDAO.load(examId);
        return enteredPassword.equals(exam.getOneTimePassword());
    }

    @Override
    public List<User> loadAllParticipantsByExamDataId(Long examDataId) {
        return examDAO.loadAllParticipantsByExamDataId(examDataId);
    }

    @Inject
    public void setExamDAO(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    /**
     * This method compares the userEntry with the correct answer and rates the exam.
     * -> sets exam.passed to true, if the participant has collected enough points.
     *
     * @param examId
     */
    @Override
    public void rateExam(Long examId) {
        List<Question> questionList = examDAO.load(examId).getExamData().getQuestionList();
        double reachedPoints = 0;
        double maxPoints = 0;
        //Über jede Frage einer examData wird iteriert und die gegebenen Antworten (userEntry) mit den
        //Lösungen (answer) verglichen
        for (Question question : questionList) {
            switch (question.getQuestionType()) {
                case MultipleSelection:
                    reachedPoints = getReachedPointsForMultipleSelection(reachedPoints, question, examId);
                    break;

                case SingleSelection:
                    reachedPoints = getReachedPointsForSingleSelection(reachedPoints, question, examId);
                    break;

                case Cloze:
                    reachedPoints = getReachedPointsForCloze(reachedPoints, question, examId);
                    break;
            }
            //Die maximalen Punkte eines exam werden zusammengezählt
            maxPoints = maxPoints + question.getPoints();
        }
        //hat der participant mehr Punkte erreicht als es die Bestehensgrenze verlangt, wird
        //in der Tabelle der bool "passed" auf 'true' gesetzt
        if (((reachedPoints / maxPoints) * 100) >= examDAO.load(examId).getExamData().getPassLimit()) {
            Exam exam = examDAO.load(examId);
            exam.setPassed(true);
            examDAO.save(exam);
        }


    }

    /**
     * returns the reached points to a multiple selection question
     *
     * @param reachedPoints reached points so far
     * @param question      current question to be rated
     * @param examId        current examId to be rated
     * @return returns the points reached to this question as double
     */
    private double getReachedPointsForMultipleSelection(double reachedPoints, Question question, Long examId) {
        double reachedPointsToThisQuestion = 0;
        double amountRightAnswers = 0;
        double amountRightEntries = 0;
        for (Answer answer : question.getAnswerList()) {
            if (answer.isSolution()) {
                amountRightAnswers = amountRightAnswers + 1;
            }
        }

        for (Answer answer : question.getAnswerList()) {

            UserEntry userEntry = userEntryService.loadUserEntryByAnswerId(answer.getId(), examId);
            if (answer.isSolution()) {
                //UserEntry holen -> ist vorhanden ja/nein?

                if (userEntry != null) {
                    reachedPointsToThisQuestion = reachedPointsToThisQuestion
                            + ((double) question.getPoints() / amountRightAnswers);
                    amountRightEntries = amountRightEntries + 1;
                } else {

                    reachedPointsToThisQuestion = reachedPointsToThisQuestion -
                            ((double) question.getPoints() / (2 * amountRightAnswers));

                }
            } else {
                //Answer ist nicht Solution
                //UserEntry holen -> ist vorhanden ja/nein?
                if (userEntry != null) {
                    reachedPointsToThisQuestion = reachedPointsToThisQuestion -
                            ((double) question.getPoints() / (2 * amountRightAnswers));

                }
            }
        }

        // Hier wird abgefragt, ob die EvaluationRule auf Full Points steht und die
        // Punkte der Frage werden entweder komplett oder garnicht addiert.
        if (question.getExamData().getEvaluationRule().ordinal() == 0) {
            if (amountRightAnswers == amountRightEntries) {
                reachedPoints = reachedPoints + question.getPoints();
            }
            return reachedPoints;
        }

        if (question.getExamData().getMinusPoints()) {
            reachedPoints = reachedPoints + Math.floor(reachedPointsToThisQuestion);
        } else {
            if (reachedPointsToThisQuestion > 0) {
                reachedPoints = reachedPoints + Math.round(reachedPointsToThisQuestion);
            }
        }
        return reachedPoints;
    }

    /**
     * returns the reached points to a single selection question
     *
     * @param reachedPoints reached points so far
     * @param question      current question to be rated
     * @param examId        current examId to be rated
     * @return returns the points reached to this question as double
     */
    private double getReachedPointsForSingleSelection(double reachedPoints, Question question, Long examId) {
        for (Answer answer : question.getAnswerList()) {
            if (answer.isSolution()) {
                //UserEntry holen -> ist vorhanden ja/nein?
                UserEntry userEntry = userEntryService.loadUserEntryByAnswerId(answer.getId(), examId);
                if (userEntry != null) {
                    reachedPoints = reachedPoints + question.getPoints();
                }
            }
        }
        return reachedPoints;
    }

    /**
     * returns the reached points to a cloze question
     *
     * @param reachedPoints reached points so far
     * @param question      current question to be rated
     * @param examId        current examId to be rated
     * @return returns the points reached to this question as double
     */
    private double getReachedPointsForCloze(double reachedPoints, Question question, Long examId) {
        double reachedPointsForThisQuestion = 0;
        for (Answer answer : question.getAnswerList()) {
            UserEntry userEntry = userEntryService.loadUserEntryByAnswerId(answer.getId(), examId);
            String[] answerTextArray = answer.getText().split(",");
            Boolean isCorrect = false;
            for (String answerText : answerTextArray) {
                isCorrect = false;

                if (answerText.toLowerCase().equals(userEntry.getBlank().toLowerCase())) {
                    isCorrect = true;
                }
            }
            if (isCorrect) {
                reachedPointsForThisQuestion = reachedPointsForThisQuestion
                        + (double) question.getPoints() / (question.getAnswerList().size());
            }
        }
        reachedPoints = reachedPoints + Math.round(reachedPointsForThisQuestion);
        return reachedPoints;
    }


    @Override
    public List<Exam> loadExamListByExamDataId(Long examDataId) {
        return examDAO.loadExamListByExamDataId(examDataId);
    }

    @Autowired
    public void setUserEntryService(UserEntryService userEntryService) {
        this.userEntryService = userEntryService;
    }
}
