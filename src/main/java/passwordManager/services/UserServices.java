package passwordManager.services;

import passwordManager.dtos.request.LoginRequest;
import passwordManager.dtos.request.LogoutRequest;

public interface UserServices {
    void login(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest);
}
