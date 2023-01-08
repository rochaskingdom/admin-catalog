package com.vinicius.admin.catalog.application.category.create;

import com.vinicius.admin.catalog.domain.category.Category;
import com.vinicius.admin.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {
    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
