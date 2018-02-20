package pl.rmitula.restfullshop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.restfullshop.model.User;
import pl.rmitula.restfullshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User unique(long id) {
        return userRepository.findOne(id);
    }

    public Long createuser(User user) {
        return userRepository.save(user).getId();
    }

    public boolean exists(long id) {
        return userRepository.exists(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserNameIgnoreCase(userName);
    }

    public void update(long id, String userName, String firstName, String lastName, String email) {
        User user = userRepository.findOne(id);

        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
    }

    public void delete(long id) throws NotFoundException {

        User user = userRepository.findOne(id);
        if(user != null) {
            userRepository.delete(id);
        } else {
            throw new NotFoundException("Not found user with id: " + id);
        }
    }

}
