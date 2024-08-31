package com.btg_pactual.domain.value_objects;

import java.util.UUID;

public class Identifier {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public static Identifier generate() {
        return new Identifier(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Identifier(id=" + id + ")";
    }
}
