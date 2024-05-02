package com.williamsilva.admin.catalogo.domain.category;

import com.williamsilva.admin.catalogo.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryId  extends Identifier {

    private final String value;

    private CategoryId(final String value) {
        Objects.requireNonNull(value, "'id' não pode ser nulo");
        this.value = value;
    }

    public static CategoryId unique() {
        return CategoryId.from(UUID.randomUUID());
    }

    public static CategoryId from(final String anId) {
        return new CategoryId(anId);
    }

    public static CategoryId from(final UUID anId) {
        return new CategoryId(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryId categoryId = (CategoryId) o;
        return Objects.equals(getValue(), categoryId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
