package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dataType.UserRole;
import de.nordakademie.iaa.mcnak.model.User;

import java.util.List;

/**
 * Public interface of the user service.
 *
 * @author Simone Vonsien
 */

public interface UserService {

    /**
     * Loads the user with the given username.
     *
     * @param username The identifier.
     * @return a user or {@code null} if no matching user was found.
     */
    User loadUser(String username);

    /**
     * Loads and returns all user entities in the database.
     *
     * @return a list of users.
     */
    List<User> loadAllUser();

    /**
     * Saves the given user.
     *
     * @param user The user to be saved.
     */
    void saveUser(User user);


    /**
     * Deletes the user with the given identifier.
     *
     * @param username The identifier.
     */
    void deleteUser(String username);

    /**
     * Checks the credentials. Returns true if username and password are correct. Otherwise false.
     *
     * @param username
     * @param hashedPw
     * @return true or false
     */
    Boolean validateCredentials(String username, String hashedPw);

    List<String> loadUserNameByRole(UserRole role);
}
