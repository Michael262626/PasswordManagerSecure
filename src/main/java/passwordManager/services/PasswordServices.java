package passwordManager.services;

import passwordManager.data.model.Password;
import passwordManager.dtos.request.*;
import passwordManager.dtos.request.dtos.DeletePasswordResponse;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.dtos.request.dtos.Response;
import passwordManager.dtos.request.dtos.UpdateResponse;

public interface PasswordServices {
    Response addPassword(PasswordCreateRequest passwordCreateRequest) throws Exception;
    GetPasswordResponse getPassword(GetPasswordRequest website) throws Exception;
    UpdateResponse updatePassword(UpdateRequest updateRequest);
    DeletePasswordResponse deletePassword(DeleteRequest deleteRequest);
    long passwordCount();
}
