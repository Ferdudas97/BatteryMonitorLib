package com.example.batterymonitorlib.metrics;

import java.util.Objects;
import java.util.UUID;

public class MeasuredActionId {
    private final UUID id;

    public MeasuredActionId(UUID id) {
        this.id = id;
    }
    public MeasuredActionId() {
        this.id = UUID.randomUUID();
    }


    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasuredActionId that = (MeasuredActionId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MeasuredActionId{" +
                "id=" + id +
                '}';
    }
}