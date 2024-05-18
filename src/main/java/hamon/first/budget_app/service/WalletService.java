package hamon.first.budget_app.service;

import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.repositories.UserRepository;
import hamon.first.budget_app.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        System.out.println(wallet);
        return wallet.orElse(null);
    }


    @Transactional
    public void increaseWalletAmount(int amount, int wallet_id){
        Wallet wallet = findById(wallet_id);
        if (wallet == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        wallet.setMoney(wallet.getMoney() + amount);
        walletRepository.save(wallet);
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
