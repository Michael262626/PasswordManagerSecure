package passwordManager.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import passwordManager.data.model.Password;

import java.util.List;

@Repository
public interface Passwords extends MongoRepository<Password, String> {
    Password findByWebsite(String website);
    List<Password> findPasswordsBy(String website);
}
