package com.dardan.rent_tool.application.usecase.category;

import com.dardan.rent_tool.application.command.CreateCategoryCommand;
import com.dardan.rent_tool.application.dto.CategoryDTO;
import com.dardan.rent_tool.application.port.in.CategoryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryUseCaseImpl implements CategoryUseCase {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryUseCaseImpl(CreateCategoryUseCase createCategoryUseCase,
                               GetCategoryUseCase getCategoryUseCase,
                               ListCategoriesUseCase listCategoriesUseCase,
                               DeleteCategoryUseCase deleteCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @Override
    public CategoryDTO create(CreateCategoryCommand command) {
        return createCategoryUseCase.execute(command);
    }

    @Override
    public CategoryDTO get(UUID id) {
        return getCategoryUseCase.execute(id);
    }

    @Override
    public List<CategoryDTO> list() {
        return listCategoriesUseCase.execute();
    }

    @Override
    public void delete(UUID id) {
        deleteCategoryUseCase.execute(id);
    }
}
