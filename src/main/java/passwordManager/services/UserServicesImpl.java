package passwordManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passwordManager.data.model.User;
import passwordManager.data.repository.Users;
import passwordManager.dtos.request.LoginRequest;
import passwordManager.dtos.request.LogoutRequest;
import passwordManager.exceptions.InvalidPasswordException;
import passwordManager.exceptions.UsernameNotFoundException;
@Service
public class UserServicesImpl implements UserServices{
    @Autowired
    Users users;
    @Override
    public void login(LoginRequest loginRequest) {
        User user = findById(loginRequest.getUsername());
        if (isPasswordIncorrect(user, loginRequest.getPassword())) throw new InvalidPasswordException("Invalid password");
        user.setLoggedIn(false);
        users.save(user);
    }
    private boolean isPasswordIncorrect(User foundUser, String password) {
        if (foundUser == null) {
            throw new NullPointerException("Diary object is null");
        }
        if (!foundUser.getPassword().equals(password)) {
            throw new InvalidPasswordException("Invalid Password");
        }
        return false;
    }
    private User findById(String username) {
        User foundUser = users.findByUsername(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        User user = findById(logoutRequest.getUsername());
        user.setLoggedIn(true);
        users.save(user);
    }
}
