package com.quiz.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="test")
@NamedQuery(name=Test.FIND_ALL, query="SELECT t FROM Test t")
public class Test {

    public static final String FIND_ALL = "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
