package passwordManager.dtos.request;

import lombok.Data;

@Data
public class GetPasswordRequest {
    private String password;
    private String website;
}
