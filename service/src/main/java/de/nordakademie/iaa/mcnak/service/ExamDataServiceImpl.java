package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dao.ExamDataDAO;
import de.nordakademie.iaa.mcnak.model.ExamData;
import de.nordakademie.iaa.mcnak.model.User;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class ExamDataServiceImpl implements ExamDataService {

    private ExamDataDAO examDataDAO;

    @Override
    public ExamData loadExamData(Long id) {
        return examDataDAO.load(id);
    }

    @Override
    public List<ExamData> loadAllExamDataByUser(User creator) {
        return examDataDAO.findAllByUser(creator);
    }

    @Override
    public void saveExamData(ExamData examData) {
        examDataDAO.save(examData);
    }

    @Override
    public void deleteExamData(Long id) {
        ExamData examData = examDataDAO.load(id);

        if(examData != null){
            examDataDAO.delete(examData);
        }
    }

    @Override
    public void updateExamDataStatus(ExamData examData) {
        examDataDAO.updateExamDataStatus(examData);
    }

    @Inject
    public void setExamDataDAO(ExamDataDAO examDataDAO) {
        this.examDataDAO = examDataDAO;
    }
}
