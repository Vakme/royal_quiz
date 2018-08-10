package com.quiz.models.rest;

import java.util.UUID;

public class QuizURI {

    private UUID uuid;
    private String name;
    private String slug;

    public QuizURI(UUID uuid, String name, String slug) {
        this.uuid = uuid;
        this.name = name;
        this.slug = slug;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
