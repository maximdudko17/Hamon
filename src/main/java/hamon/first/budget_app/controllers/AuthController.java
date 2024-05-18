package hamon.first.budget_app.controllers;

import hamon.first.budget_app.DTO.AuthenticationDTO;
import hamon.first.budget_app.DTO.UserDTO;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.service.AuthService;
import hamon.first.budget_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, AuthService authService, ModelMapper modelMapper) {
        this.userService = userService;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Регистрация пользователя", tags = {"add"})
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserDTO userDTO){
        try {
            return new ResponseEntity<>(authService.register(convertToUser(userDTO)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Ошибка при регистрации", HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Аутентификация пользователя", tags = {"login"})
    @PostMapping("/login")
    public ResponseEntity<?> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
        return ResponseEntity.ok(authService.login(authenticationDTO));
    }


    public User convertToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
