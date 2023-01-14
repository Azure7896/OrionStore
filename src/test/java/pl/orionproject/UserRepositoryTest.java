package pl.orionproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.orionproject.model.User;
import pl.orionproject.repository.UserRepository;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addUserToDatabaseTest() {
        Date date = new Date();
        User user = new User("testowy@gmail.com", "12345", date);

        //User savedUser = userRepository.save(user);

        //User existUser =  entityManager.find(User.class, savedUser.getEmail());
       // assert equals(existUser.getEmail().equals(user.getEmail()));
    }


}
