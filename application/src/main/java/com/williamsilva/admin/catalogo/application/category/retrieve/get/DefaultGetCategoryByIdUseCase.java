package com.williamsilva.admin.catalogo.application.category.retrieve.get;

import com.williamsilva.admin.catalogo.domain.category.CategoryGateway;
import com.williamsilva.admin.catalogo.domain.category.CategoryID;
import com.williamsilva.admin.catalogo.domain.exceptions.DomainException;
import com.williamsilva.admin.catalogo.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(String anId) {
        final CategoryID categoryID = CategoryID.from(anId);

        return categoryGateway.findById(categoryID)
                .map(CategoryOutput::from)
                .orElseThrow(notFount(anId));
    }

    private static Supplier<DomainException> notFount(String anId) {
        return () -> DomainException.with(new Error("Categoria de código %s não encontrada".formatted(anId)));
    }
}
