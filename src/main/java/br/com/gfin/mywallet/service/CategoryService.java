package br.com.gfin.mywallet.service;

import br.com.gfin.mywallet.controller.dto.CreateUpdateCategoryDto;
import br.com.gfin.mywallet.entity.Category;
import br.com.gfin.mywallet.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category create(CreateUpdateCategoryDto newCategoryDto) {
        Category category = new Category();
        category.setDescription(newCategoryDto.getDescription());

        return categoryRepository.save(category);
    }

    public Category update(Long id, String description) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            category.get().setDescription(description);
            return categoryRepository.save(category.get());
        }

        throw new Exception("category not found");

    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
