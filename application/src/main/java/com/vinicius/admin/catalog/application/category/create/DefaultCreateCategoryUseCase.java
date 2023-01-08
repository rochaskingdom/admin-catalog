package com.vinicius.admin.catalog.application.category.create;

import com.vinicius.admin.catalog.domain.category.Category;
import com.vinicius.admin.catalog.domain.category.CategoryGateway;
import com.vinicius.admin.catalog.domain.validation.hadler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand command) {
        final var name = command.name();
        final var description = command.description();
        final var isActive = command.isActive();

        final var category = Category.newCategory(name, description, isActive);
        category.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.from(this.categoryGateway.create(category));
    }
}
