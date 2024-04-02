package passwordManager.dtos.request.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class GetPasswordResponse {
    private String password;
}
