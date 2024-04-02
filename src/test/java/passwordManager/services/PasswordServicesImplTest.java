package passwordManager.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import passwordManager.data.model.Password;
import passwordManager.data.repository.Passwords;
import passwordManager.dtos.request.DeleteRequest;
import passwordManager.dtos.request.GetPasswordRequest;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.GetPasswordResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static passwordManager.utils.Mapper.map3;

@SpringBootTest
class PasswordServicesImplTest {
    @Autowired
    private PasswordServices passwordServices;
    @Autowired
    private Passwords passwords;
    @BeforeEach
    public void setUp(){
        passwords.deleteAll();
    }
    @Test
    void addPassword() {
        PasswordCreateRequest passwordCreateRequest = new PasswordCreateRequest();
        passwordCreateRequest.setPassword("password");
        passwordServices.addPassword(passwordCreateRequest);
        assertEquals(1, passwordServices.passwordCount());
    }

    @Test
    void getPassword() {
        GetPasswordRequest getPasswordRequest = new GetPasswordRequest();
        PasswordCreateRequest passwordCreateRequest = new PasswordCreateRequest();
        passwordCreateRequest.setPassword("password");
        passwordServices.addPassword(passwordCreateRequest);
        getPasswordRequest.setWebsite(passwordCreateRequest.getWebsite());
        getPasswordRequest.setPassword(passwordCreateRequest.getPassword());

        GetPasswordResponse getPasswordResponse = new GetPasswordResponse();
        getPasswordResponse.setPassword(getPasswordRequest.getPassword());

        passwordServices.getPassword(getPasswordRequest);
        assertEquals(getPasswordResponse,passwordServices.getPassword(getPasswordRequest));
    }

    @Test
    void updatePassword() {
        PasswordCreateRequest passwordCreateRequest = new PasswordCreateRequest();
        passwordCreateRequest.setPassword("password");
        passwordCreateRequest.setWebsite("website");
        passwordServices.addPassword(passwordCreateRequest);

        long initialCount = passwordServices.passwordCount();

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setWebsite(passwordCreateRequest.getWebsite());
        updateRequest.setNewPassword("newPassword");
        passwordServices.updatePassword(updateRequest);

        long updatedCount = passwordServices.passwordCount();

        assertEquals(initialCount, updatedCount);
    }
    @Test
    void deletePassword() {
        PasswordCreateRequest passwordCreateRequest = new PasswordCreateRequest();
        passwordCreateRequest.setPassword("password");
        passwordCreateRequest.setWebsite("website.com");
        passwordServices.addPassword(passwordCreateRequest);

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setWebsite(passwordCreateRequest.getWebsite());
        passwordServices.deletePassword(deleteRequest);
        assertEquals(0, passwordServices.passwordCount());
    }
}