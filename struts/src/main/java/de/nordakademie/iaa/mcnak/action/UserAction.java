package de.nordakademie.iaa.mcnak.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.mcnak.dataType.UserRole;
import de.nordakademie.iaa.mcnak.model.User;
import de.nordakademie.iaa.mcnak.service.UserService;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simone Vonsien
 * @author Marten Moraal
 */
public class UserAction extends ActionSupport {

    /**
     * List of all available Roles to display in UI
     */
    private List<UserRole> roleNames;

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * The user currently edited.
     */
    private User user;

    /**
     * The user's identifier.
     */
    private String userName;

    /**
     * List of all users
     */
    private List<User> userList;

    /**
     * the new password to save
     */
    private String newPassword;

    /**
     * Constructor for initialization
     */
    public UserAction() {
        roleNames = new ArrayList<UserRole>();

        UserRole[] roles = UserRole.values();

        for (int i = 0; i < roles.length; i++) {
            UserRole role = roles[i];

            roleNames.add(role);
        }
    }

    public String loadAllUser() throws Exception {
        userList = userService.loadAllUser();

        return SUCCESS;
    }

    public String loadUser() {
        user = userService.loadUser(userName);
        return SUCCESS;
    }

    public String saveUser() {
        userService.saveUser(user);
        return SUCCESS;
    }

    public String deleteUser() {
        userService.deleteUser(userName);
        return SUCCESS;
    }

    public String resetPassword() {
        System.out.println("user: " + user + " newPassword: " + newPassword + userName);
        user = userService.loadUser(userName);
        user.setHashedPw(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userService.saveUser(user);
        return SUCCESS;
    }

    public List<UserRole> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<UserRole> roleNames) {
        this.roleNames = roleNames;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
