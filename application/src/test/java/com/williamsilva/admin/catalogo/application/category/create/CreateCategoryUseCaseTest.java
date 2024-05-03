package com.williamsilva.admin.catalogo.application.category.create;


import com.williamsilva.admin.catalogo.domain.category.CategoryGateway;
import com.williamsilva.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    void givenAValidCommand_whenCallCreateCategoryUseCase_thenShouldReturnCategoryId() {
        var expectedName = "Filmes";
        var expectedDescription = "Categoria de filmes";
        var expectedIsActive = true;

        var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, times(1)).create(argThat(aCategory ->
                    Objects.equals(expectedName, aCategory.getName())
                            && Objects.equals(expectedDescription, aCategory.getDescription())
                            && Objects.equals(expectedIsActive, aCategory.isActive())
                            && Objects.nonNull(aCategory.getId())
                            && Objects.nonNull(aCategory.getCreatedAt())
                            && Objects.nonNull(aCategory.getUpdatedAt())
                            && Objects.isNull(aCategory.getDeletedAt())
            ));
    }

    @Test
    void givenAnInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
        final String expectedName = null;
        final var expectedDescription = "Categoria de filmes";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'nome' não pode ser nulo";
        final var expectedErrorCount = 1;

        var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        DomainException actualException = assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        Mockito.verify(categoryGateway, times(0)).create(any());
    }

    @Test
    void givenAValidCommandWithInactiveCategory_whenCallCreateCategoryUseCase_thenShouldReturnInactiveCategoryId() {
        var expectedName = "Filmes";
        var expectedDescription = "Categoria de filmes";
        var expectedIsActive = false;

        var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.nonNull(aCategory.getDeletedAt())
        ));
    }

    @Test
    void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {
        var expectedName = "Filmes";
        var expectedDescription = "Categoria de filmes";
        var expectedIsActive = true;
        var expectedErrorMessage = "Gateway error";

        var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenThrow(new IllegalArgumentException(expectedErrorMessage));

        final var actualException = assertThrows(IllegalArgumentException.class, () -> useCase.execute(aCommand));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        Mockito.verify(categoryGateway, times(1)).create(argThat(aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
        ));
    }
}