package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.service.UserService;

import java.util.Optional;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String userList(Model model,
                           @RequestParam("minAge") Optional<Integer> minAge,
                           @RequestParam("maxAge") Optional<Integer> maxAge) {
        logger.info("User list. With minAge = {} and maxAge = {}", minAge, maxAge);

        if(!minAge.isPresent() && !maxAge.isPresent()){
            model.addAttribute("users", userService.findAll());
        }else if(minAge.isPresent() && !maxAge.isPresent()){
            model.addAttribute("users", userService.filterByMinAge(minAge));
        }else if(!minAge.isPresent() && maxAge.isPresent()){
            model.addAttribute("users", userService.filterByMaxAge(maxAge));
        }else{
            model.addAttribute("users", userService.filterByAge(minAge, maxAge));
        }
        return "users";
    }

    @GetMapping("new")
    public String createUser(Model model) {
        logger.info("Create user form");

        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping
    public String saveUser(User user) {
        logger.info("Save user method");

        userService.save(user);
        return "redirect:/user";
    }
}
