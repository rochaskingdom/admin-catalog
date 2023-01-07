package com.vinicius.admin.catalog.domain.category;

import com.vinicius.admin.catalog.domain.validation.Error;
import com.vinicius.admin.catalog.domain.validation.ValidationHandler;
import com.vinicius.admin.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
