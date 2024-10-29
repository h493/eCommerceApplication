package com.example.ecom.service;

import com.example.ecom.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Resource Not Found"));

        categories.remove(category);
        return "Category with categoryID: " + categoryId + " deleted Successfully!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> oldCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if(oldCategory.isPresent()){
            Category exisitingCategory = oldCategory.get();
            exisitingCategory.setCategoryName(category.getCategoryName());
            return exisitingCategory;
        }else{
            throw  new ResponseStatusException(NOT_FOUND, "Category Not Found");
        }
    }
}
