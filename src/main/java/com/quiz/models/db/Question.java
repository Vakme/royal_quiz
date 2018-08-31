package com.quiz.models.db;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  @NotNull
  private long id;

  @Column(name = "content")
  @NotNull
  private String content;

  @Column(name = "num")
  @NotNull
  private int num;

  @ManyToOne(fetch=FetchType.LAZY)
  @JsonBackReference
  @JoinColumn(name="quiz")
  private Quiz quiz;

  @JsonProperty("answers")
  @JsonManagedReference
  @OneToMany(mappedBy="question", fetch = FetchType.EAGER)
  private List<Answer> answers;

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

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }
}
