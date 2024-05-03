package com.williamsilva.admin.catalogo.domain.category;

import com.williamsilva.admin.catalogo.domain.exceptions.DomainException;
import com.williamsilva.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        var expectedName = "Filmes";
        var expectedDescription = "Filmes de ação";
        var expectedIsActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertTrue(category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());
    }

    @Test
    void givenAnInValidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        String expectedName = null;
        var expectedErrorCount = 1;
        var expectedErrorMessage = "'nome' não pode ser nulo";
        var expectedDescription = "Filmes de ação";
        var expectedIsActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    void givenAnInValidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        String expectedName = " ";
        var expectedErrorCount = 1;
        var expectedErrorMessage = "'nome' não pode ser vazio";
        var expectedDescription = "Filmes de ação";
        var expectedIsActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    void givenAnInValidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        String expectedName = "ws ";
        var expectedErrorCount = 1;
        var expectedErrorMessage = "'nome' deve ter no mínimo 3 caracteres e no máximo 30";
        var expectedDescription = "Filmes de ação";
        var expectedIsActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    void givenAnInValidNameLengthMoreThan30_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        String expectedName = """
                Acima de tudo, é fundamental ressaltar que a crescente influência da mídia obstaculiza a 
                apreciação da importância das condições financeiras e administrativas exigidas.
                """;
        var expectedErrorCount = 1;
        var expectedErrorMessage = "'nome' deve ter no mínimo 3 caracteres e no máximo 30";
        var expectedDescription = "Filmes de ação";
        var expectedIsActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

    @Test
    void givenAnValidEmptyDescription_whenCallNewCategory_thenInstantiateACategory() {
        String expectedName = "Filmes";
        var expectedDescription = " ";
        var expectedIsActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertTrue(category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNull(category.getDeletedAt());

    }

    @Test
    void givenAnValidFalseIsActive_whenCallNewCategory_thenInstantiateACategory() {
        String expectedName = "Filmes";
        var expectedDescription = "Filmes de ação";
        var expectedIsActive = false;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
        assertFalse(category.isActive());
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertNotNull(category.getDeletedAt());

    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, true);

        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        assertTrue(aCategory.isActive());
        assertNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.desactivate();

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, false);

        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        assertFalse(aCategory.isActive());
        assertNotNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.activate();

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory("Film", "A categoria", expectedIsActive);

        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory =
                Category.newCategory("Film", "A categoria", true);

        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        assertTrue(aCategory.isActive());
        assertNull(aCategory.getDeletedAt());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertFalse(aCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNotNull(aCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory("Filmes", "A categoria", expectedIsActive);

        assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertTrue(aCategory.isActive());
        assertEquals(createdAt, actualCategory.getCreatedAt());
        assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        assertNull(aCategory.getDeletedAt());
    }


}