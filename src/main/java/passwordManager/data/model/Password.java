package passwordManager.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import passwordManager.dtos.request.dtos.Response;

import java.util.HashMap;
@Data
public class Password {
        @Id
        private String id;
        private String website;
        private String username;
        private String password;
        @DBRef
        private HashMap<String, String> passwords = new HashMap<>();
}
