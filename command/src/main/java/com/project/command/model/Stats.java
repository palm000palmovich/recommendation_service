package com.project.command.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stats")
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "count")
    private int count;

    @OneToOne
    private Rule rule;

    public void setCount(int count) {
        this.count = count;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Long getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public Rule getRule() {
        return rule;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", count=" + count +
                ", rule=" + rule +
                '}';
    }
}
