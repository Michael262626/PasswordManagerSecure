package passwordManager.dtos.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class UpdateRequest {
    private String newPassword;
    private String website;
}
