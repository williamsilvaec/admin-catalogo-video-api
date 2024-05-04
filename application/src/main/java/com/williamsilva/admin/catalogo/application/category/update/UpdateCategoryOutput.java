package com.williamsilva.admin.catalogo.application.category.update;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(CategoryID id) {

    public static UpdateCategoryOutput from(Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
