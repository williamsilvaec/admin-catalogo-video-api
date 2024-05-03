package com.williamsilva.admin.catalogo.application.category.create;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryId;

public record CreateCategoryOutput(CategoryId id) {

    public static CreateCategoryOutput from(Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
