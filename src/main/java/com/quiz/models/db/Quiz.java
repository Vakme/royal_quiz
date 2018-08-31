package com.quiz.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Represents basic info about quiz
 */
@Entity
@Table(name = "quiz")
public class Quiz {

    /**
     * Used to distinguish quiz visibility
     */
    public enum Privacy {

        /**
         * Quiz is accessible only via link
         */
        @JsonProperty("private")
        PRIVATE ("private"),

        /**
         * Quiz is visible in public catalogue
         */
        @JsonProperty("public")
        PUBLIC("public");

        private final String type;

        private Privacy(String privacyType) {
           type = privacyType;
        }
    }

    /**
     * Used to distinguish the method of solving
     */
    public enum Type {

        /**
         * Each answer has a weight from 0 to 1
         * Results are shown as a list of how many % user scored per each category
         */
        @JsonProperty("category")
        CATEGORY("category"),

        /**
         * Each answer has a weight either 0 or 1
         * Results are shown as a list of how many % good and bad answers user submitted
         * Full solution is yet to be implemented
         */
        @JsonProperty("test")
        TEST("test");

        private final String type;

        private Type(String quizType) {
            type = quizType;
        }
    }

    /**
     * Unique generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    /**
     * Name (title) of the quiz
     */
    @Column(name = "name")
    @NotNull
    private String name;

    /**
     * Privacy
     */
    @Column(name = "privacy")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    /**
     * Type
     */
    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     * Unique ID generated when quiz is added
     * Used to represent quiz by API and to share it via link
     */
    @Column(name = "uuid")
    private UUID uuid;

    /**
     * Quiz rating
     */
    @Column(name = "rating")
    private double rating;

    /**
     * Creator of quiz
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "userinfo")
    private User userinfo;

    /**
     * List of questions
     */
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
