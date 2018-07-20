package com.quiz.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "resultanswer")
public class ResultAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="resultid")
    private Result resultId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="answerid")
    private Answer answerId;

    @Column(name = "weight")
    @NotNull
    private double weight;

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

}
