package com.williamsilva.admin.catalogo.domain.category;

import com.williamsilva.admin.catalogo.domain.validation.Error;
import com.williamsilva.admin.catalogo.domain.validation.ValidationHandler;
import com.williamsilva.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(Category category, ValidationHandler aHandler) {
        super(aHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'nome' não pode ser nulo"));
        }
    }
}
