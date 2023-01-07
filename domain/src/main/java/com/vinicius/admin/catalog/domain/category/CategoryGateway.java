package com.vinicius.admin.catalog.domain.category;

import com.vinicius.admin.catalog.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category category);

    void deleteById(CategoryID categoryID);

    Optional<Category> findById(CategoryID categoryID);

    Category update(Category category);

    Pagination<Category> findAll(CategorySearchQuery query);
}
