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
    private UUID productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_text")
    private String productText;

    @OneToMany(mappedBy = "recommendation",
        cascade = CascadeType.ALL)
    private List<Rule> rule;

    public RecommendationsByRules(UUID product_id, String product_name,
                                  String product_text, List<Rule> rule) {
        this.productId = product_id;
        this.productName = product_name;
        this.productText = product_text;
        this.rule = rule;

        //Reverse link for rules
        for (Rule r : rule){
            r.setRecommendation(this);
        }
    }

    //Getters and setters
    public RecommendationsByRules(){}

    public UUID getProduct_id() {
        return productId;
    }

    public String getProduct_name() {
        return productName;
    }

    public String getProduct_text() {
        return productText;
    }

    public List<Rule> getRule() {
        return rule;
    }

    public void setProduct_name(String product_name){
        this.productName = product_name;
    }

    public void setProduct_text(String product_text) {
        this.productText = product_text;
    }

    public void setRule(List<Rule> rule) {
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationsByRules that = (RecommendationsByRules) o;
        return Objects.equals(productId, that.productId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "RecommendationsByRules{" +
                "product_id=" + productId +
                ", product_name='" + productName + '\'' +
                ", product_text='" + productText + '\'' +
                ", rule=" + rule +
                '}';
    }
}
