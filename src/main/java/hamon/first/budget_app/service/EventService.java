package hamon.first.budget_app.service;

import hamon.first.budget_app.models.Category;
import hamon.first.budget_app.models.Event;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.repositories.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getReasonEventsByWalletAndUser(Category category, User user){
        return eventRepository.getEventsByReasonCategoryAndUser(category.getId(), user.getId());
    }

    public List<Event> getConsequenceEventsByWalletAndUser(Category category, User user){
        return eventRepository.getEventsByConsequenceCategoryAndUser(category, user);
    }

    @Transactional
    public void save(Event event){
        eventRepository.save(event);
    }

    @Transactional
    public void deleteByUser(User user){
        eventRepository.deleteByUser(user);
    }

    public void addEvent(Category reasonCategory, Category consequencesCategory, String description,
                         double chance, boolean good, int amount, boolean death, User user){
        Event event = new Event();
        event.setReasonCategory(reasonCategory);
        event.setConsequenceCategory(consequencesCategory);
        event.setDescription(description);
        event.setChance(chance);
        event.setGood(good);
        event.setAmount(amount);
        event.setDeath(death);
        event.setUser(user);
        eventRepository.save(event);
    }

}
