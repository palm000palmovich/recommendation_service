package com.project.command.DTO;

import java.util.List;

public class RecommendationsByRuleDTO {
    private String product_name;
    private String product_text;
    private List<RuleDTO> rule;


    public RecommendationsByRuleDTO(String product_name, String product_text, List<RuleDTO> rule) {
        this.product_name = product_name;
        this.product_text = product_text;
        this.rule = rule;
    }

    //Getters and setters
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_text() {
        return product_text;
    }

    public void setProduct_text(String product_text) {
        this.product_text = product_text;
    }

    public List<RuleDTO> getRules() {
        return rule;
    }

    public void setRules(List<RuleDTO> rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "RecommendationsByRuleDTO{" +
                "product_name='" + product_name + '\'' +
                ", product_text='" + product_text + '\'' +
                ", rules=" + rule +
                '}';
    }
}
