package com.dardan.rent_tool.infrastructure.adapters.in.rest.controllers;

import com.dardan.rent_tool.application.command.CreateCategoryCommand;
import com.dardan.rent_tool.application.dto.CategoryDTO;
import com.dardan.rent_tool.application.usecase.category.CreateCategoryUseCase;
import com.dardan.rent_tool.application.usecase.category.DeleteCategoryUseCase;
import com.dardan.rent_tool.application.usecase.category.GetCategoryUseCase;
import com.dardan.rent_tool.application.usecase.category.ListCategoriesUseCase;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.request.CreateCategoryRequest;
import com.dardan.rent_tool.infrastructure.adapters.in.rest.response.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase,
                              ListCategoriesUseCase listCategoriesUseCase,
                              GetCategoryUseCase getCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CreateCategoryRequest request) {
        CategoryDTO dto = createCategoryUseCase.execute(new CreateCategoryCommand(request.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(dto));
    }

    @GetMapping
    public List<CategoryResponse> list() {
        return listCategoriesUseCase.execute().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable UUID id) {
        return toResponse(getCategoryUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteCategoryUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    private CategoryResponse toResponse(CategoryDTO dto) {
        return new CategoryResponse(dto.getId(), dto.getName());
    }
}
