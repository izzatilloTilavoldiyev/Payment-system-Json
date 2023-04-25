package org.example.Izzatillo.domain.model;

import java.util.UUID;

public abstract class BaseModel {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
