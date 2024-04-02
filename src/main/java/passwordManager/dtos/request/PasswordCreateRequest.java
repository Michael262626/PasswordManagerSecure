package passwordManager.dtos.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class PasswordCreateRequest {
    private String password;
    private String website;
}
