package hamon.first.budget_app.service;

import hamon.first.budget_app.DTO.AuthenticationDTO;
import hamon.first.budget_app.DTO.JwtResponseDTO;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.repositories.UserRepository;
import hamon.first.budget_app.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    private final JWTUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public AuthService(JWTUtil jwtUtil, PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public JwtResponseDTO register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return generateResponse(user);
    }

    public JwtResponseDTO login(AuthenticationDTO authenticationDTO){
        Optional<User> user = userService.loadUserByUsername(authenticationDTO.getUsername());
        if (user.isPresent()){
            if (!passwordEncoder.matches(authenticationDTO.getPassword(), user.get().getPassword())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный пароль");
            }
            return generateResponse(user.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с таким именем не найден");
        }
    }

    private JwtResponseDTO generateResponse(User user){
        return new JwtResponseDTO(jwtUtil.generateToken(user.getUsername()));
    }

}
