package de.nordakademie.iaa.mcnak.service;

import de.nordakademie.iaa.mcnak.dao.UserDAO;
import de.nordakademie.iaa.mcnak.dataType.UserRole;
import de.nordakademie.iaa.mcnak.model.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RobMacWin on 31.10.2015.
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public User loadUser(String username) {
        return userDAO.load(username);
    }

    @Override
    public List<User> loadAllUser() {
        return userDAO.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setUserName(user.getUserName().toLowerCase());
        userDAO.save(user);
    }

    @Override
    public void deleteUser(String username) {
        User user = userDAO.load(username);

        if(user != null){
            userDAO.delete(user);
        }
    }

    @Override
    public Boolean validateCredentials(String username, String hashedPw) {
        User user = userDAO.load(username);

        System.out.println("inputhash: "+ hashedPw + "db: " + user.getHashedPw());
        if(user != null && user.getHashedPw().equals(hashedPw)){
            return true;
        }
        return false;
    }

    @Override
    public List<String> loadUserNameByRole(UserRole role) {
        List<User> userList = loadUserByRole(role);
        List<String> userNameList = new ArrayList<>();
        for(int i = 0; i < userList.size(); i++){
            userNameList.add(userList.get(i).getUserName());
        }
        return userNameList;
    }

    private List<User> loadUserByRole(UserRole role) {
        return userDAO.loadUserListByRole(role);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
