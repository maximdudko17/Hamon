package hamon.first.budget_app.service;

import hamon.first.budget_app.DTO.AuthenticationDTO;
import hamon.first.budget_app.DTO.JwtResponseDTO;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.repositories.UserRepository;
import hamon.first.budget_app.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    private final JWTUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserService userService;

    private final WalletService walletService;

    private final EventService eventService;

    private final CategoryService categoryService;

    @Autowired
    public AuthService(JWTUtil jwtUtil, PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService, WalletService walletService, EventService eventService, CategoryService categoryService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
        this.walletService = walletService;
        this.eventService = eventService;
        this.categoryService = categoryService;
    }

    @Transactional
    public JwtResponseDTO register(User user){
        Random random = new Random();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Wallet wallet = new Wallet();
        wallet.setMoney(random.nextInt(3000) + 3000);
        wallet.setUser(user);
        user.setWallet(wallet);
        wallet.setCreated_at(null);
        wallet.setUpdated_at(null);
        userRepository.save(user);
        walletService.save(wallet);
        createEventForUser(user);
        JwtResponseDTO jwtResponseDTO = generateResponse(user);
        jwtResponseDTO.setMessage("Добро пожаловать в игру по распределению бюджета!" +
                "У вас одна цель: выжить, а сможете ли вы это сделать решит удача и холодный расчёт");
        return jwtResponseDTO;
    }

    public JwtResponseDTO login(AuthenticationDTO authenticationDTO){
        Optional<User> user = userService.loadUserByUsername(authenticationDTO.getUsername());
        if (user.isPresent()){
            if (!passwordEncoder.matches(authenticationDTO.getPassword(), user.get().getPassword())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный пароль");
            }
            JwtResponseDTO jwtResponseDTO = generateResponse(user.get());
            jwtResponseDTO.setMessage("Добро пожаловать в игру по распределению бюджета! \n" +
                    "У вас одна цель: выжить, а сможете ли вы это сделать решит удача и холодный расчёт");
            return jwtResponseDTO;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с таким именем не найден");
        }
    }

    private void createEventForUser(User user){
        eventService.addEvent(categoryService.getCategoryById(4),
                categoryService.getCategoryById(7),
                "У вас развилась язва желудка, куда бы вложить деньги хм...",
                0.3, false, 100, false, user);
        eventService.addEvent(categoryService.getCategoryById(8),
                categoryService.getCategoryById(7),
                "Как-то в жизни не хватает развлечений, депресняк...",
                0.3, false, 100, false, user);
        eventService.addEvent(categoryService.getCategoryById(5),
                categoryService.getCategoryById(7),
                "Много ходишь, ноги начинают болеть",
                0.3, false, 100, false, user);
        eventService.addEvent(categoryService.getCategoryById(4),
                categoryService.getCategoryById(7),
                "Вы сталее здоровее",
                0.3, true, 100, false, user);
        eventService.addEvent(categoryService.getCategoryById(8),
                categoryService.getCategoryById(7),
                "Развлечения пошли вам на пользу, депрессия не грозит",
                0.3, true, 100, false, user);
        eventService.addEvent(categoryService.getCategoryById(7),
                categoryService.getCategoryById(7),
                "Вы слишком мало вкладывали в медицину, вы погибли...",
                0.1, false, 100, true, user);
        eventService.addEvent(categoryService.getCategoryById(4),
                categoryService.getCategoryById(4),
                "Вы слишком мало вкладывали в еду, вы погибли...",
                0.1, false, 100, true, user);
        eventService.addEvent(categoryService.getCategoryById(5),
                categoryService.getCategoryById(5),
                "Вы слишком мало вкладывали в транспорт, гопники не дремлют...",
                0.1, false, 100, true, user);
        eventService.addEvent(categoryService.getCategoryById(6),
                categoryService.getCategoryById(6),
                "Вы слишком мало вкладывали в проживание, теперь вы бомж...",
                0.1, false, 100, true, user);
        eventService.addEvent(categoryService.getCategoryById(8),
                categoryService.getCategoryById(8),
                "Вы слишком мало вкладывали в развлечения, теперь вы пнд...",
                0.1, false, 100, true, user);
    }

    private JwtResponseDTO generateResponse(User user){
        return new JwtResponseDTO(jwtUtil.generateToken(user.getUsername()));
    }

}
