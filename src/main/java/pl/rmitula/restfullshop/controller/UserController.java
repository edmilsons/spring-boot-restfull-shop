package pl.rmitula.restfullshop.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;import pl.rmitula.restfullshop.model.User;
import pl.rmitula.restfullshop.model.dto.UserDto;
import pl.rmitula.restfullshop.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static pl.rmitula.restfullshop.controller.converter.Converter.fromUserDto;
import static pl.rmitula.restfullshop.controller.converter.Converter.toUserDto;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> get() {
        return userService.getUsers().stream().map((User user) -> toUserDto(user)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public HttpEntity<UserDto> findById(@PathVariable(name = "id") long id) {
        User user = userService.unique(id);
        if (user != null) {
            return new ResponseEntity<>(toUserDto(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/findByUserName/{userName}")
    public HttpEntity<UserDto> findByUserName(@PathVariable(name = "userName") String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            return new ResponseEntity<>(toUserDto(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public HttpEntity<Long> create(@RequestBody @Valid UserDto userDto) {
        User userName = userService.findByUserName(userDto.getUsername());

        if (userName == null) {
            return new ResponseEntity<>(userService.createuser(fromUserDto(userDto)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable(name = "id") long id, @RequestBody @Valid UserDto user) {
        userService.update(id, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus delete(@PathVariable(name = "id") long id) throws NotFoundException {
        User user = userService.unique(id);
        if (user != null) {
            userService.delete(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }




}
