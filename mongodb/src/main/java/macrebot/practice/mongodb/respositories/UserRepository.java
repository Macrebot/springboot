package macrebot.practice.mongodb.respositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import macrebot.practice.mongodb.models.User;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findByName(String name);

    Boolean deleteUserById(Long id);

}
