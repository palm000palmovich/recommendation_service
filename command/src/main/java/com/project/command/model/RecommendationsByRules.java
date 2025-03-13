package com.project.command.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "recommendations")
public class RecommendationsByRules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID product_id;
    private String product_name;
    private String product_text;

    @OneToMany(mappedBy = "recommendations")
    private Collection<RuleDTO> rule;

    public RecommendationsByRules(UUID product_id, String product_name, String product_text, RuleDTO rule) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_text = product_text;
    }

    public RecommendationsByRules(){}

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
