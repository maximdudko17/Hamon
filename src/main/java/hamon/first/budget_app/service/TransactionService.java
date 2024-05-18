package hamon.first.budget_app.service;

import hamon.first.budget_app.models.Category;
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
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final CategoryRepository categoryRepository;

    private final WalletRepository walletRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.walletRepository = walletRepository;
    }

    public List<Wallet> findAll(){
        return walletRepository.findAll();
    }

    public Wallet findById(int id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElse(null);
    }

    public List<Transaction> findWalletTransactions(Wallet wallet){
        return transactionRepository.findByWallet(wallet);
    }

    public List<Transaction> findByCategory(Category category){
        return transactionRepository.findByCategory(category);
    }

    public List<Transaction> findByCategoryAndWallet(Category category, Wallet wallet){
        return transactionRepository.findByCategoryAndWallet(category, wallet);
    }

    @Transactional
    public void save(Transaction transaction){
       // transaction.setCategory(categoryRepository.findByName());
        transactionRepository.save(transaction);
    }



    @Transactional
    public void delete(Transaction transaction){
        transactionRepository.delete(transaction);
    }
}
