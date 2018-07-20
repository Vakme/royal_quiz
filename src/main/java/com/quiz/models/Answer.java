package com.quiz.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="question")
    private Question question;

    @OneToMany(mappedBy="answerId")
    private List<ResultAnswer> results;

    public List<ResultAnswer> getResults() {
        return results;
    }

    public void setResults(List<ResultAnswer> results) {
        this.results = results;
    }

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


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
