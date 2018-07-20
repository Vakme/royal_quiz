package com.quiz.models;

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
        @JsonProperty("quiz")
        QUIZ ("quiz"),

        @JsonProperty("exam")
        EXAM("exam");

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

    @Column(name = "questionnum")
    @NotNull
    private long questionnum;

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

    @OneToMany(mappedBy="quiz")
    private List<Question> questions;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getQuestionnum() {
        return questionnum;
    }

    public void setQuestionnum(long questionnum) {
        this.questionnum = questionnum;
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


    public User getUser() {
        return userinfo;
    }

    public void setUser(User user) {
        this.userinfo = user;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
