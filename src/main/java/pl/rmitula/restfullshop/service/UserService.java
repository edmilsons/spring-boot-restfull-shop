package pl.rmitula.restfullshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.restfullshop.exception.BadRequestException;
import pl.rmitula.restfullshop.exception.ConflictException;
import pl.rmitula.restfullshop.model.User;
import pl.rmitula.restfullshop.repository.UserRepository;
import pl.rmitula.restfullshop.exception.NotFoundException;
import javax.validation.ConstraintViolationException;
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

    public User findById(long id) {
        User user = userRepository.findById(id);

        if(user != null) {
            return user;
        } else {
            throw new NotFoundException("Not found user with id: " + id);
        }
    }

    public User findByUserName(String userName) throws NotFoundException {
        User user = userRepository.findByUserNameIgnoreCase(userName);

        if(user != null) {
            return user;
        } else {
            throw new NotFoundException("Not found user with username: " + userName);
        }
    }

    public void update(long id, String userName, String firstName, String lastName, String email) throws NotFoundException, ConflictException, BadRequestException {
        //TODO: Empty fields validation
        User user = userRepository.findById(id);
        User checkUserName = userRepository.findByUserNameIgnoreCase(userName);
        User checkEmail = userRepository.findByEmailIgnoreCase(email);

        if(user != null) {

            if(checkUserName != null && checkUserName.getId() != id) {
                throw new ConflictException("This username is already associated with another user account.");
            }

            if(checkEmail != null && checkEmail.getId() != id) {
                throw new ConflictException("This email is already associated with another user account.");
            }

            user.setUsername(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);

            try {
                userRepository.save(user);

            } catch (ConstraintViolationException e) {
                throw new BadRequestException();
            }
        } else {
            throw new NotFoundException("Not found user with id: " + id);
        }
    }

    public void delete(long id) throws NotFoundException {
        User user = userRepository.findById(id);

        if(user != null) {
            userRepository.delete(id);
        } else {
            throw new NotFoundException("Not found user with id: " + id);
        }
    }

    public Long create(User user) throws ConflictException {
        //TODO: Empty fields validation
        User userName = userRepository.findByUserNameIgnoreCase(user.getUsername());
        User email = userRepository.findByEmailIgnoreCase(user.getEmail());

        if(userName == null && email == null) {
            try {
                return userRepository.save(user).getId();
            } catch (ConstraintViolationException e) {
                throw new BadRequestException();
            }
        } else {
            throw new ConflictException("This username or email is already associated with a different user account.");
        }
    }
}
