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
    public List<UserDTO> getAllUser(){
        return userService.findAll().stream().map(this::toUserDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/wallets")
    public List<Wallet> getAllWallets(UsernamePasswordAuthenticationToken auth){
        return walletService.findPersonWallets(userService.loadUserByUsername(auth.getName()).get());

    }


    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id){
        return toUserDTO(userService.findById(id)); //Into JSON
    }

    @PostMapping("/200")
    public void create(@ModelAttribute("user") User user) {
        userService.save(user);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public UserDTO createPost(@RequestBody UserDTO userDTO) {
//        User user = toUser(userDTO);
//        userService.save(user);
//        return toUserDTO(user);
//    }

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