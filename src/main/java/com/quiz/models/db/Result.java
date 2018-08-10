package com.quiz.models.db;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    @Column(name = "content")
    @NotNull
    private String content;

    @Column(name = "result_number")
    @NotNull
    @JsonProperty("number")
    private int resultNumber;

    @OneToMany(mappedBy="resultId")
    private List<ResultAnswer> answers;

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
