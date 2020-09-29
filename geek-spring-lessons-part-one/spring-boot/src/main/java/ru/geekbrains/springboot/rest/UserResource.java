package ru.geekbrains.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.persist.entity.User;
import ru.geekbrains.springboot.service.UserService;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<User> findAll(){
        return userService.findAll();
    }


    @GetMapping(path = "/{id}/id")
    public User findById(@PathVariable("id") Long id){
        return userService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        if(user.getId() != null){
            throw new IllegalArgumentException("Id not found");
        }
        userService.save(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping(path = "/{id}/id")
    public void deleteUser(@PathVariable("id") Long id){
        userService.findById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception){
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> IllegalArgumentException(NotFoundException exception){
        return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
    }

}
