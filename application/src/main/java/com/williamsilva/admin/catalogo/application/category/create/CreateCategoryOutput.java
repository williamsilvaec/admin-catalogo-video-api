package com.williamsilva.admin.catalogo.application.category.create;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {

    public static CreateCategoryOutput from(Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
