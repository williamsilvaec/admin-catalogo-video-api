package com.williamsilva.admin.catalogo.application.category.create;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryGateway;
import com.williamsilva.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final String aName = aCommand.name();
        final String aDescription = aCommand.description();
        final boolean isActive = aCommand.isActive();

        final Category category = Category.newCategory(aName, aDescription, isActive);
        category.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.with(this.categoryGateway.create(category));
    }
}
