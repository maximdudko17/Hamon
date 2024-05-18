package hamon.first.budget_app.controllers;

import hamon.first.budget_app.requests.WeekBudgetRequest;
import hamon.first.budget_app.service.GameService;
import hamon.first.budget_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    private final UserService userService;

    @Autowired
    public GameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @PostMapping("/nextWeek")
    public ResponseEntity<?> nextWeek(@RequestBody WeekBudgetRequest weekBudgetRequest,
                                      UsernamePasswordAuthenticationToken auth){

        gameService.nextWeek(weekBudgetRequest, userService.loadUserByUsername(auth.getName()).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
