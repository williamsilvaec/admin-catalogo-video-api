package com.williamsilva.admin.catalogo.application.category.update;

import com.williamsilva.admin.catalogo.domain.category.Category;
import com.williamsilva.admin.catalogo.domain.category.CategoryGateway;
import com.williamsilva.admin.catalogo.domain.category.CategoryID;
import com.williamsilva.admin.catalogo.domain.exceptions.DomainException;
import com.williamsilva.admin.catalogo.domain.validation.Error;
import com.williamsilva.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.function.Supplier;

import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand aCommand) {
        final String name = aCommand.name();
        final String description = aCommand.description();
        final CategoryID categoryId = CategoryID.from(aCommand.id());
        final boolean isActive = aCommand.isActive();

        Category aCategory = categoryGateway.findById(categoryId)
                .orElseThrow(noFound(categoryId));

        final Notification notification = Notification.create();

        aCategory.update(name, description, isActive).validate(notification);

        return notification.hasErrors() ? Either.left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category category) {
        return Try(() -> this.categoryGateway.update(category))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private static Supplier<DomainException> noFound(CategoryID categoryId) {
        Error error = new Error(String.format("Categoria com id %s não encontrada", categoryId.getValue()));
        return () -> DomainException.with(error);
    }
}
