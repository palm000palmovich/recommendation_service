package com.project.command.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query")
    private String query;
    @Column(name = "arguments")
    private List<String> arguments;
    @Column(name = "negate")
    private boolean negate;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private RecommendationsByRules recommendation;

    public Rule(String query, List<String> arguments, boolean negate) {
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
    }
    public Rule(){}

    //Getters
    public String getQuery() {
        return query;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public boolean isNegate() {
        return negate;
    }


    //Setters


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule ruleDTO = (Rule) o;
        return Objects.equals(id, ruleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
