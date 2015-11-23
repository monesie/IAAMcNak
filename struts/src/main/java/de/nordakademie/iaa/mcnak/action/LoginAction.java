package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.dataType.UserRole;
import de.nordakademie.iaa.mcnak.model.User;
import de.nordakademie.iaa.mcnak.service.UserService;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author Simone Vonsien
 */
public class LoginAction extends ActionSupport {

    /**
     * the current username
     */
    private String username;

    /**
     * the entered password
     */
    private String password;

    /**
     * the current
     */
    private User user;

    /**
     * the current user role
     */
    private UserRole role;

    /**
     * temporary server responses
     */
    private String serverResponse;

    /**
     * the user service
     */
    private UserService userService;

    @Override
    public String execute() throws Exception {

        // Check if User is already logged in and redirect to home

        // Show Login Screen
        serverResponse = "Please Log In with your Username and Password";
        return SUCCESS;
    }

    /**
     * This method checks, if the password is correct.
     *
     * @return the role of an User or ERROR
     */
    public String validatePassword() {

        this.username = username.toLowerCase();

        this.setUser(userService.loadUser(username));
        if (this.user != null && validateCredentials()) {
            Map session = ActionContext.getContext().getSession();

            session.put("loggedIn", "true");
            session.put("username", username);
            session.put("context", new Date());
            session.put("role", user.getRole());
            role = user.getRole();
            return role.toString();
        }

        serverResponse = "Username or Password was incorrect.";
        return ERROR;
    }

    /**
     * This method deletes the session information
     *
     * @return SUCCESS
     */
    public String logout() {
        Map session = ActionContext.getContext().getSession();
        session.clear();
        return SUCCESS;
    }

    private Boolean validateCredentials() {
        String hashedPw = DigestUtils.md5DigestAsHex(password.getBytes());
        return userService.validateCredentials(username, hashedPw);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
