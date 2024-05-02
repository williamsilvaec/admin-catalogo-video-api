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


}