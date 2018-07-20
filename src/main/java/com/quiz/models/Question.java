package com.quiz.models;


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

  @Column(name = "answernum")
  @NotNull
  private long answernum;

  @Column(name = "content")
  @NotNull
  private String content;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="quiz")
  private Quiz quiz;

  @OneToMany(mappedBy="question")
  private List<Answer> answers;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getAnswernum() {
    return answernum;
  }

  public void setAnswernum(long answernum) {
    this.answernum = answernum;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
