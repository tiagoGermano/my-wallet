package br.com.gfin.mywallet.controller;

import br.com.gfin.mywallet.controller.dto.CreateUpdateCategoryDto;
import br.com.gfin.mywallet.entity.Category;
import br.com.gfin.mywallet.exception.EntityNotFoundException;
import br.com.gfin.mywallet.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CreateUpdateCategoryDto newCategoryDto){
        Category category = categoryService.create(newCategoryDto);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody CreateUpdateCategoryDto categoryDto, @PathVariable long id){
        try {
            Category category = categoryService.update(id, categoryDto);
            return ResponseEntity.ok(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        try {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
