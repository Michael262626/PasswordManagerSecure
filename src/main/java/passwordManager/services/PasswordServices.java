package passwordManager.services;

import passwordManager.data.model.Password;
import passwordManager.dtos.request.DeleteRequest;
import passwordManager.dtos.request.GetPasswordRequest;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.DeletePasswordResponse;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.dtos.request.dtos.Response;
import passwordManager.dtos.request.dtos.UpdateResponse;

public interface PasswordServices {
    Response addPassword(PasswordCreateRequest passwordCreateRequest);
    GetPasswordResponse getPassword(GetPasswordRequest website);
    UpdateResponse updatePassword(UpdateRequest updateRequest);
    DeletePasswordResponse deletePassword(DeleteRequest deleteRequest);
    long passwordCount();
}
