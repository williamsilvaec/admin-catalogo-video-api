package com.williamsilva.admin.catalogo.application.category.retrieve.list;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryGateway;
import com.williamsilva.admin.catalogo.domain.pagination.Pagination;
import com.williamsilva.admin.catalogo.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(SearchQuery anSearchQuery) {
        Pagination<Category> categoryPagination = categoryGateway.findAll(anSearchQuery);
        return categoryPagination.map(CategoryListOutput::from);
    }
}
