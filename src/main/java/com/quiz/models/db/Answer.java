package com.quiz.models.db;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represents answer content
 */
@Entity
@Table(name = "answer")
public class Answer {

    /**
     * Unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    /**
     * Answer itself
     */
    @Column(name = "content")
    private String content;

    /**
     * Ordering number (unique per question)
     */
    @Column(name = "num")
    @NotNull
    private int num;

    /**
     * Question this answer belongs to
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="question")
    private Question question;

    /**
     * List of weights for each result
     */
    @JsonIgnore
    @OneToMany(mappedBy="answerId")
    private List<ResultAnswer> results;

    @JsonIgnore
    public List<ResultAnswer> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<ResultAnswer> results) {
        this.results = results;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
