package hamon.first.budget_app.service;

import hamon.first.budget_app.models.Event;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.requests.WeekBudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GameService {

    private final CategoryService categoryService;

    private final EventService eventService;

    @Autowired
    public GameService(CategoryService categoryService, EventService eventService) {
        this.categoryService = categoryService;
        this.eventService = eventService;
    }

    public void nextWeek(WeekBudgetRequest weekBudgetRequest, User user){
        if (weekBudgetRequest.getFood() < 0 || weekBudgetRequest.getHome() < 0 ||
            weekBudgetRequest.getMedicine() < 0 || weekBudgetRequest.getTransport() < 0 ||
        weekBudgetRequest.getEntertainment() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (weekBudgetRequest.getFood() + weekBudgetRequest.getHome() +
        weekBudgetRequest.getMedicine() + weekBudgetRequest.getTransport() +
        weekBudgetRequest.getEntertainment() > user.getWallet().getMoney()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (weekBudgetRequest.getFood() < categoryService.getCategoryById(4).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(4), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(4), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
            }
        }
        if (weekBudgetRequest.getTransport() < categoryService.getCategoryById(5).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(5), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(5), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
            }
        }
        if (weekBudgetRequest.getHome() < categoryService.getCategoryById(6).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(6), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(6), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
            }
        }
        if (weekBudgetRequest.getMedicine() < categoryService.getCategoryById(7).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(7), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(7), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
            }
        }
        if (weekBudgetRequest.getEntertainment() < categoryService.getCategoryById(8).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(8), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(8), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
            }
        }
    }

}
