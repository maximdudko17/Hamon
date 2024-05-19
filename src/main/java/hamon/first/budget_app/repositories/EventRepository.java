package hamon.first.budget_app.repositories;

import hamon.first.budget_app.models.Category;
import hamon.first.budget_app.models.Event;
import hamon.first.budget_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {


    @Query(value = """
    select * from event
    where reason_category_id = ?1 and event.user_id = ?2
    """, nativeQuery = true)
    List<Event> getEventsByReasonCategoryAndUser(int categoryId, int userId);

    List<Event> getEventsByConsequenceCategoryAndUser(Category category, User user);

    void deleteByUser(User user);
}
