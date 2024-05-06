package com.williamsilva.admin.catalogo.infrastructure.category;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryGateway;
import com.williamsilva.admin.catalogo.domain.category.CategoryID;
import com.williamsilva.admin.catalogo.domain.pagination.Pagination;
import com.williamsilva.admin.catalogo.domain.pagination.SearchQuery;

import java.util.Optional;

public class CategoryMySQLGateway implements CategoryGateway {

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public void deleteById(CategoryID anId) {

    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return Optional.empty();
    }

    @Override
    public Category update(Category aCategory) {
        return null;
    }

    @Override
    public Pagination<Category> findAll(SearchQuery query) {
        return null;
    }
}
