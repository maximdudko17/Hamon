package hamon.first.budget_app.service;

import hamon.first.budget_app.models.Event;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.requests.WeekBudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    public List<Event> nextWeek(WeekBudgetRequest weekBudgetRequest, User user){
        List<Event> happenedEvents = new ArrayList<>();
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
                eventService.save(event);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(4), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
                eventService.save(event);
            }
        }
        Event eventTemp = chooseOnWeight(eventService.getConsequenceEventsByWalletAndUser(categoryService.getCategoryById(4), user));
        if (eventTemp.isDeath()){
            gameOver(user);
        }
        else {
            if (eventTemp.isGood()){
                categoryService.getCategoryById(4).setSurvivalAmount(categoryService.getCategoryById(4).getSurvivalAmount() + 100);
            }
            else {
                categoryService.getCategoryById(4).setSurvivalAmount(categoryService.getCategoryById(4).getSurvivalAmount() - 100);
            }
        }
        happenedEvents.add(eventTemp);
        if (weekBudgetRequest.getTransport() < categoryService.getCategoryById(5).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(5), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
                eventService.save(event);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(5), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
                eventService.save(event);
            }
        }
        eventTemp = chooseOnWeight(eventService.getConsequenceEventsByWalletAndUser(categoryService.getCategoryById(5), user));
        happenedEvents.add(eventTemp);
        if (eventTemp.isDeath()){
            gameOver(user);
        }
        else {
            if (eventTemp.isGood()){
                categoryService.getCategoryById(5).setSurvivalAmount(categoryService.getCategoryById(5).getSurvivalAmount() + eventTemp.getAmount());
            }
            else {
                categoryService.getCategoryById(5).setSurvivalAmount(categoryService.getCategoryById(5).getSurvivalAmount() - eventTemp.getAmount());
            }
        }

        if (weekBudgetRequest.getHome() < categoryService.getCategoryById(6).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(6), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
                eventService.save(event);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(6), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
                eventService.save(event);
            }
        }
        eventTemp = chooseOnWeight(eventService.getConsequenceEventsByWalletAndUser(categoryService.getCategoryById(6), user));
        happenedEvents.add(eventTemp);
        if (eventTemp.isDeath()){
            gameOver(user);
        }
        else {
            if (eventTemp.isGood()){
                categoryService.getCategoryById(6).setSurvivalAmount(categoryService.getCategoryById(6).getSurvivalAmount() + eventTemp.getAmount());
            }
            else {
                categoryService.getCategoryById(6).setSurvivalAmount(categoryService.getCategoryById(6).getSurvivalAmount() - eventTemp.getAmount());
            }
        }
        if (weekBudgetRequest.getMedicine() < categoryService.getCategoryById(7).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(7), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
                eventService.save(event);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(7), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
                eventService.save(event);
            }
        }
        eventTemp = chooseOnWeight(eventService.getConsequenceEventsByWalletAndUser(categoryService.getCategoryById(7), user));
        happenedEvents.add(eventTemp);
        if (eventTemp.isDeath()){
            gameOver(user);
        }
        else {
            if (eventTemp.isGood()){
                categoryService.getCategoryById(7).setSurvivalAmount(categoryService.getCategoryById(7).getSurvivalAmount() + eventTemp.getAmount());
            }
            else {
                categoryService.getCategoryById(7).setSurvivalAmount(categoryService.getCategoryById(7).getSurvivalAmount() - eventTemp.getAmount());
            }
        }
        if (weekBudgetRequest.getEntertainment() < categoryService.getCategoryById(8).getSurvivalAmount()){
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(8), user);
            for (Event event: events){
                event.setChance(event.getChance() + 0.1);
                eventService.save(event);
            }
        }
        else{
            List<Event> events = eventService.getReasonEventsByWalletAndUser(categoryService.getCategoryById(8), user);
            for (Event event: events){
                event.setChance(event.getChance() - 0.1);
                eventService.save(event);
            }
        }
        eventTemp = chooseOnWeight(eventService.getConsequenceEventsByWalletAndUser(categoryService.getCategoryById(8), user));
        happenedEvents.add(eventTemp);
        if (eventTemp.isDeath()){
            gameOver(user);
        }
        else {
            if (eventTemp.isGood()){
                categoryService.getCategoryById(8).setSurvivalAmount(categoryService.getCategoryById(8).getSurvivalAmount() + eventTemp.getAmount());
            }
            else {
                categoryService.getCategoryById(8).setSurvivalAmount(categoryService.getCategoryById(8).getSurvivalAmount() - eventTemp.getAmount());
            }
        }
        return happenedEvents;
    }

    private Event gameOver(User user){
        Event event = new Event();
        event.setDescription("Кажется это поражение...");
        eventService.deleteByUser(user);
        return event;
    }

    private Event chooseOnWeight(List<Event> events) {
        double completeWeight = 0.0;
        for (Event event : events)
            completeWeight += event.getChance();
        double r = Math.random() * completeWeight;
        double countWeight = 0.0;
        for (Event event : events) {
            countWeight += event.getChance();
            if (countWeight >= r)
                return event;
        }
        return events.get(0);
    }

}
