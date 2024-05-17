package hamon.first.budget_app.service;

import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.repositories.UserRepository;
import hamon.first.budget_app.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public List<Wallet> findAll(){
        return walletRepository.findAll();
    }

    public Wallet findById(int id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElse(null);
    }


    @Transactional
    public void save(Wallet wallet){
        enrichSensor(wallet);
        walletRepository.save(wallet);
    }

    private void enrichSensor(Wallet wallet) {
        wallet.setCreated_at(LocalDateTime.now());
    }


    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }


}
