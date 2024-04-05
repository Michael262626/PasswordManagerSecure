package passwordManager.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import passwordManager.data.model.User;

public interface Users extends MongoRepository<User, String> {
    User findByUsername(String username);
}
