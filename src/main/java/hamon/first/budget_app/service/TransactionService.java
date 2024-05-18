package hamon.first.budget_app.service;

//<<<<<<< HEAD
//=======
import hamon.first.budget_app.DTO.TransactionDTO;
import hamon.first.budget_app.models.Transaction;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.repositories.CategoryRepository;
import hamon.first.budget_app.repositories.TransactionRepository;
import hamon.first.budget_app.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
//>>>>>>> origin/main
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final WalletRepository walletRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }



    public Wallet findById(int id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        System.out.println(wallet);
        return wallet.orElse(null);
    }



    public List<Transaction> findWalletTransactions(Wallet wallet){
        return transactionRepository.findByWallet(wallet);
    }

    @Transactional
    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Transactional
    public void delete(Transaction transaction){
        transactionRepository.delete(transaction);
    }

}
