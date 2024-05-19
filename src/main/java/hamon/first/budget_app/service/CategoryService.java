package hamon.first.budget_app.service;

import hamon.first.budget_app.models.Category;
import hamon.first.budget_app.repositories.CategoryRepository;
import hamon.first.budget_app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(int id){
        return categoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

}
