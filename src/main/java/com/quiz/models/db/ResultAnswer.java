package com.quiz.models.db;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents connection between each result and answer that influences the final score
 */
@Entity
@Table(name = "resultanswer")
public class ResultAnswer {

    /**
     * Unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    /**
     * Result influenced by the answer
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="resultid")
    private Result resultId;

    /**
     * Answer that changes the final score
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="answerid")
    private Answer answerId;

    /**
     * Weight (how the score is changed)
     */
    @Column(name = "weight")
    @NotNull
    private double weight;

    /**
     * Ordering number of the result (unique per answer)
     */
    @JsonProperty("number")
    @Transient
    private int resultNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Result getResultid() {
        return resultId;
    }

    public void setResultid(Result resultid) {
        this.resultId = resultid;
    }


    public Answer getAnswerid() {
        return answerId;
    }

    public void setAnswerid(Answer answerid) {
        this.answerId = answerid;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public int getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(int resultNumber) {
        this.resultNumber = resultNumber;
    }
}
