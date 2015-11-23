package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dao.UserEntryDAO;
import de.nordakademie.iaa.mcnak.model.UserEntry;

import javax.inject.Inject;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class UserEntryServiceImpl implements UserEntryService {

    UserEntryDAO userEntryDAO;

    @Override
    public UserEntry loadUserEntry(Long id) {
        return userEntryDAO.load(id);
    }

    @Override
    public void saveUserEntry(UserEntry userEntry) {
        userEntryDAO.save(userEntry);
    }

    @Override
    public void deleteUserEntry(UserEntry userEntry) {
        userEntryDAO.delete(userEntry);
    }

    @Override
    public void deleteUserEntry(Long id) {
        UserEntry userEntry = userEntryDAO.load(id);

        if (userEntry != null) {
            userEntryDAO.delete(userEntry);
        }
    }

    @Override
    public UserEntry loadUserEntryByAnswerId(Long answerId, Long examId) {
        return userEntryDAO.findUserEntryByAnswerId(answerId, examId);
    }

    @Inject
    public void setUserEntryDAO(UserEntryDAO userEntryDAO) {
        this.userEntryDAO = userEntryDAO;
    }
}
