package hamon.first.budget_app.controllers;

import hamon.first.budget_app.DTO.UserDTO;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserDTO> getAllSensor(){
        return userService.findAll().stream().map(this::toUserDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public UserDTO getSensor(@PathVariable("id") int id){
        return toUserDTO(userService.findById(id)); //Into JSON
    }

    @PostMapping("/200")
    public void create(@ModelAttribute("user") User user) {
        userService.save(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO createPost(@RequestBody UserDTO userDTO) {
        User user = toUser(userDTO);
        userService.save(user);
        return toUserDTO(user);
    }


    private UserDTO toUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    private User toUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}