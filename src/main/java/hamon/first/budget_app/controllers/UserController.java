package hamon.first.budget_app.controllers;

import hamon.first.budget_app.DTO.UserDTO;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.service.UserService;
import hamon.first.budget_app.service.WalletService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final WalletService walletService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, WalletService walletService, ModelMapper modelMapper) {
        this.userService = userService;
        this.walletService = walletService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.findAll().stream().map(this::toUserDTO)
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getAllWallets(UsernamePasswordAuthenticationToken auth){
        return new ResponseEntity<>(walletService.findPersonWallets
                (userService.loadUserByUsername(auth.getName()).get()), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id){
        return new ResponseEntity<>(toUserDTO(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute("user") User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid UserDTO userDTO) {

        userService.save(toUser(userDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private UserDTO toUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    private User toUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}