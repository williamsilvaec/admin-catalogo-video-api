package com.williamsilva.admin.catalogo.domain.category;

import com.williamsilva.admin.catalogo.domain.validation.Error;
import com.williamsilva.admin.catalogo.domain.validation.ValidationHandler;
import com.williamsilva.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 30;

    private final Category category;

    public CategoryValidator(Category category, ValidationHandler aHandler) {
        super(aHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        String name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'nome' não pode ser nulo"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'nome' não pode ser vazio"));
            return;
        }

        final int nameLength = name.trim().length();
        if (nameLength < NAME_MIN_LENGTH || nameLength > NAME_MAX_LENGTH) {
            this.validationHandler()
                    .append(new Error("'nome' deve ter no mínimo 3 caracteres e no máximo 30"));
        }
    }
}
