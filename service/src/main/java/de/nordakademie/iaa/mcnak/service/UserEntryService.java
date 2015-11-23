package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.model.UserEntry;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public interface UserEntryService {

    /**
     * Loads the userEntry with the given id.
     * @param id The identifier.
     * @return a userEntry or {@code null} if no matching userEntry was found.
     */
    UserEntry loadUserEntry(Long id);


    /**
     * Saves the given userEntry.
     * @param userEntry The userEntry to be saved.
     */
    void saveUserEntry(UserEntry userEntry);

    /**
     * Deletes the given userEntry.
     * @param userEntry The userEntry to be deleted.
     */
    void deleteUserEntry(UserEntry userEntry);

    /**
     * Deletes the userEntry with the given identifier.
     * @param id The identifier.
     */
    void deleteUserEntry(Long id);

    /**
     * Loads userEntry to a answer
     * @param answerId the unique identifier to the answer
     * @return
     */
    UserEntry loadUserEntryByAnswerId(Long answerId, Long examId);

}
