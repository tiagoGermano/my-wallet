package br.com.gfin.mywallet.controller;

import br.com.gfin.mywallet.controller.dto.CreateUpdateCategoryDto;
import br.com.gfin.mywallet.entity.Category;
import br.com.gfin.mywallet.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<Category> listAll(){
        return categoryService.findAll();
    }

    @PostMapping
    public Category save(@Valid @RequestBody CreateUpdateCategoryDto newCategoryDto){
        System.out.println(newCategoryDto.getDescription());
        return categoryService.create(newCategoryDto);
    }

}
