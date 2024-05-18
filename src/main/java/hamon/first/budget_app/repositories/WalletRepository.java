package hamon.first.budget_app.repositories;

import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    List<Wallet> findByUser(User user);
}
