package hamon.first.budget_app.repositories;

import hamon.first.budget_app.models.Category;
import hamon.first.budget_app.models.Transaction;
import hamon.first.budget_app.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByWallet(Wallet wallet);

    List<Transaction> findByCategory(Category category);

    List<Transaction> findByCategoryAndWallet(Category category, Wallet wallet);

}
