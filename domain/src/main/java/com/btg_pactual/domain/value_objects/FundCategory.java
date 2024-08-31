package com.btg_pactual.domain.value_objects;

import java.util.Set;

public class FundCategory {
    private static final Set<String> VALID_CATEGORIES = Set.of("FPV", "FIC");
    private final String category;

    public FundCategory(String category) {
        this.category = validateCategory(category);
    }

    private String validateCategory(String category) {
        if (!VALID_CATEGORIES.contains(category)) {
            throw new IllegalArgumentException("Categoría no válida: " + category + ". Las permitidas son: " + VALID_CATEGORIES);
        }
        return category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "FundCategory(" + category + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FundCategory that = (FundCategory) obj;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }
}
