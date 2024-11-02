package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CategoryServiceImpl implements CategoryService {

   // private List<Category> categories = new ArrayList<>();
  //  private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        /**
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Resource Not Found"));

        categoryRepository.delete(category);
        return "Category with categoryID: " + categoryId + " deleted Successfully!!";
         **/

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Resource Not found"));

        categoryRepository.delete(savedCategory);
        return "Category with categoryID: " + categoryId + " deleted Successfully!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {


      Category savedCategory = categoryRepository.findById(categoryId)
              .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Resource Not found"));

      /**
        Optional<Category> oldCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();


        if(oldCategory.isPresent()){
            Category exisitingCategory = oldCategory.get();
            exisitingCategory.setCategoryName(category.getCategoryName());
            categoryRepository.save(exisitingCategory);
            return exisitingCategory;
        }else{
            throw  new ResponseStatusException(NOT_FOUND, "Category Not Found");
        }
       **/

      category.setCategoryId(categoryId);
      savedCategory = categoryRepository.save(category);
      return savedCategory;
    }
}
