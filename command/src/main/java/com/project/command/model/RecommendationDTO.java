package com.project.command.model;

public class RecommendationDTO {
    private String productName;
    private String productId;
    private String productText;


    @Override
    public String toString() {
        return "recommendations: [\n" + "{\n" +
                "\"name\": " + productName + "," +
                "\"id\": " + productId + "," +
                "\"text\": " + productText +
                "}\n" + "]\n";
    }
}
