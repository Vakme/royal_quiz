package com.quiz.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "quiz")
public class Quiz {

    public enum Privacy {
        @JsonProperty("private")
        PRIVATE ("private"),

        @JsonProperty("public")
        PUBLIC("public");

        private final String type;

        private Privacy(String privacyType) {
           type = privacyType;
        }
    }

    public enum Type {
        @JsonProperty("category")
        CATEGORY("category"),

        @JsonProperty("test")
        TEST("test");

        private final String type;

        private Type(String quizType) {
            type = quizType;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "privacy")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "rating")
    private double rating;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "userinfo")
    private User userinfo;

    @JsonProperty("questions")
    @JsonManagedReference
    @OneToMany(mappedBy="quiz", fetch = FetchType.EAGER)
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(@NotNull String name, @NotNull Privacy privacy, @NotNull Type type, UUID uuid, double rating, User userinfo) {
        this.name = name;
        this.privacy = privacy;
        this.type = type;
        this.uuid = uuid;
        this.rating = rating;
        this.userinfo = userinfo;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public @NotNull Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }


    public @NotNull Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public @NotNull UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
