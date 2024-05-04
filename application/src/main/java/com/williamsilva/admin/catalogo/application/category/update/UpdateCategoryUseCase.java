package com.williamsilva.admin.catalogo.application.category.update;

import com.williamsilva.admin.catalogo.application.UseCase;
import com.williamsilva.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
