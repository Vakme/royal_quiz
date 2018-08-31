package com.quiz.models.db;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represents possible final result of the quiz
 */
@Entity
@Table(name = "result")
public class Result {

    /**
     * Unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    /**
     * Result itself
     */
    @Column(name = "content")
    @NotNull
    @JsonProperty("content")
    private String content;

    /**
     * Ordering number (one per quiz)
     */
    @Column(name = "result_number")
    @NotNull
    @JsonProperty("number")
    private int resultNumber;

    /**
     * Used to map result to each answer that uses it
     */
    @OneToMany(mappedBy="resultId")
    private List<ResultAnswer> answers;

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    public List<ResultAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ResultAnswer> answers) {
        this.answers = answers;
    }

    public int getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(int resultNumber) {
        this.resultNumber = resultNumber;
    }
}
