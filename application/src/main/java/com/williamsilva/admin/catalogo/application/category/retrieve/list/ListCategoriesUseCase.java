package com.williamsilva.admin.catalogo.application.category.retrieve.list;

import com.williamsilva.admin.catalogo.application.UseCase;
import com.williamsilva.admin.catalogo.domain.pagination.Pagination;
import com.williamsilva.admin.catalogo.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
