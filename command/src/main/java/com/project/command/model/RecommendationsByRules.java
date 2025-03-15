package com.project.command.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "recommendations")
public class RecommendationsByRules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID product_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_text")
    private String product_text;

    @OneToMany(mappedBy = "recommendation")
    private List<Rule> rule;

    public RecommendationsByRules(UUID product_id, String product_name, String product_text, List<Rule> rule) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_text = product_text;
        this.rule = rule;
    }

    public RecommendationsByRules(){}

    public UUID getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_text() {
        return product_text;
    }

    public List<Rule> getRule() {
        return rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationsByRules that = (RecommendationsByRules) o;
        return Objects.equals(product_id, that.product_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(product_id);
    }
}
