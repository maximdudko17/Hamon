package hamon.first.budget_app.service;

import hamon.first.budget_app.models.Category;
import hamon.first.budget_app.repositories.CategoryRepository;
import hamon.first.budget_app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final TransactionRepository transactionRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(int id){
        return categoryRepository.findById(id).orElseThrow(RuntimeException::new);

    }

}
