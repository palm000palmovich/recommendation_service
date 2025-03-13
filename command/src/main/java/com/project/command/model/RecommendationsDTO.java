package com.project.command.model;

import java.util.Objects;
import java.util.UUID;

public class RecommendationsDTO {
    private String productName;
    private String productText;

    public RecommendationsDTO(String productName, String productText) {
        this.productName = productName;
        this.productText = productText;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    @Override
    public String toString() {
        return "recommendations: [\n" + "{\n" +
                "\"name\": " + productName + "," +
                "\"text\": " + productText +
                "}\n" + "]\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationsDTO that = (RecommendationsDTO) o;
        return Objects.equals(productName, that.productName) && Objects.equals(productText, that.productText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productText);
    }
}
