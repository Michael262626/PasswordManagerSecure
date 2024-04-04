package passwordManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passwordManager.data.model.Password;
import passwordManager.data.repository.Passwords;
import passwordManager.dtos.request.DeleteRequest;
import passwordManager.dtos.request.GetPasswordRequest;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.DeletePasswordResponse;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.dtos.request.dtos.Response;
import passwordManager.dtos.request.dtos.UpdateResponse;
import passwordManager.exceptions.IncorrectPasswordException;
import passwordManager.exceptions.PasswordExistException;
import passwordManager.exceptions.PasswordNotFoundException;

import java.util.Optional;

import static passwordManager.utils.Mapper.*;

@Service
public class PasswordServicesImpl implements PasswordServices {

    @Autowired
    Passwords passwords;

    @Override
    public Response addPassword(PasswordCreateRequest passwordCreateRequest) {
        if(isPasswordExisting(passwordCreateRequest.getPassword())) throw new PasswordExistException("Password already exist");
        Password password = map(passwordCreateRequest);
        Response result = map(password);
        passwords.save(password);
        return result;
    }


    @Override
    public GetPasswordResponse getPassword(GetPasswordRequest website) {
        Password optionalPassword = passwords.findByWebsite(website.getWebsite());
        GetPasswordResponse result = map3(optionalPassword);
            if (optionalPassword.getPassword() == null || optionalPassword.getPassword().isEmpty()) {
                throw new IncorrectPasswordException("Password was not found for website: " + website);
            }
            return result;
    }

    @Override
    public DeletePasswordResponse deletePassword(DeleteRequest deleteRequest) {
        Optional<Password> optionalPassword = Optional.ofNullable(passwords.findByWebsite(deleteRequest.getWebsite()));
        if (optionalPassword.isPresent()) {
            Password password = optionalPassword.get();
            passwords.delete(password);
            return mapDeleteResponseWith(password);
        } else {
            throw new PasswordNotFoundException("Password not found for website: " + deleteRequest.getWebsite());
        }
    }

    @Override
    public long passwordCount() {
        return passwords.findAll().size();
    }

    @Override
    public UpdateResponse updatePassword(UpdateRequest updateRequest) {
        Password password = findPasswordBy(updateRequest.getWebsite());
        Password set = map1(updateRequest);
        passwords.save(set);
        UpdateResponse updateResponse = map2(password);
        passwords.delete(password);
        return updateResponse;
    }
    private Password findPasswordBy(String website) {
        Password foundPassword = passwords.findByWebsite(website);
        if (foundPassword == null) throw new PasswordNotFoundException(String.format("%s not found", website));
        return foundPassword;
    }
    private boolean isPasswordExisting(String foundPassword){
        for (Password password : passwords.findAll()) {
            if (password.getPasswords().containsValue(foundPassword)) {
                return true;
            }
        }
        return false;
    }
}

