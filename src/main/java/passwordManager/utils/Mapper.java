package passwordManager.utils;

import passwordManager.data.model.Password;
import passwordManager.dtos.request.PasswordCreateRequest;
import passwordManager.dtos.request.UpdateRequest;
import passwordManager.dtos.request.dtos.DeletePasswordResponse;
import passwordManager.dtos.request.dtos.GetPasswordResponse;
import passwordManager.dtos.request.dtos.Response;
import passwordManager.dtos.request.dtos.UpdateResponse;

public class Mapper {
    public static Password map(PasswordCreateRequest passwordCreateRequest) {
        validate(passwordCreateRequest.getPassword());
        validate1(passwordCreateRequest.getWebsite());
        Password password1 = new Password();
        password1.setPassword(passwordCreateRequest.getPassword());
        password1.setWebsite(passwordCreateRequest.getWebsite());
        return password1;
    }
    private static void validate (String password){
        if(password == null) throw new NullPointerException("Password field is required");
    }
    private static void validate1(String website){
        if(website == null) throw  new NullPointerException("Website is required");
    }
    public static Response map(Password password){
        Response response = new Response();
        response.setPassword(password.getPassword());
        return response;
    }
    public static Password map1(UpdateRequest updateRequest){
        validate(updateRequest.getNewPassword());
        validate(updateRequest.getWebsite());
        Password password = new Password();
        password.setPassword(updateRequest.getNewPassword());
        password.setWebsite(updateRequest.getWebsite());
        return password;
    }
    public static UpdateResponse map2(Password password){
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setPassword(password.getPassword());
        return  updateResponse;
    }
    public static DeletePasswordResponse mapDeleteResponseWith(Password password) {
        validate(password.getPassword());
        DeletePasswordResponse deletePasswordResponse = new DeletePasswordResponse();
        deletePasswordResponse.setWebsite(password.getWebsite());
        return deletePasswordResponse;
    }
    public static GetPasswordResponse map3(Password password){
        validate(password.getPassword());
        GetPasswordResponse getPasswordResponse = new GetPasswordResponse();
        getPasswordResponse.setPassword(password.getPassword());
        return getPasswordResponse;
    }
}
