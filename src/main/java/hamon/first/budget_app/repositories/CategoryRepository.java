package hamon.first.budget_app.repositories;

import hamon.first.budget_app.models.Category;
import hamon.first.budget_app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //Category findByName();
}
