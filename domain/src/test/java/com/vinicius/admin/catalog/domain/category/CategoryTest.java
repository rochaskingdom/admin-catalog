package com.vinicius.admin.catalog.domain.category;

import com.vinicius.admin.catalog.domain.exceptions.DomainException;
import com.vinicius.admin.catalog.domain.validation.hadler.ThrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceieErrorInstantiateACategory() {
        final String expectedName = null;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceieErrorInstantiateACategory() {
        final String expectedName = "  ";
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceieErrorInstantiateACategory() {
        final String expectedName = "Vi ";
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceieErrorInstantiateACategory() {
        final String expectedName = """
                Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a complexidade dos estudos
                efetuados prepara-nos para enfrentar situações atípicas decorrentes da gestão inovadora da qual
                fazemos parte. A prática cotidiana prova que o desenvolvimento contínuo de distintas
                """;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAValidEmptyDescription_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "  ";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidFalseIsActive_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivate() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var category = Category.newCategory(expectedName, expectedDescription, true);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var createdAt = category.getCreatedAt();
        final var updatedAt = category.getUpdatedAt();

        assertTrue(category.isActive());
        assertNull(category.getDeletedAt());

        final var actualCategory = category.deactivate();

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(category.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivate() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var category = Category.newCategory(expectedName, expectedDescription, false);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var createdAt = category.getCreatedAt();
        final var updatedAt = category.getUpdatedAt();

        assertFalse(category.isActive());
        assertNotNull(category.getDeletedAt());

        final var actualCategory = category.activate();

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(category.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNull(actualCategory.getDeletedAt());
    }
}