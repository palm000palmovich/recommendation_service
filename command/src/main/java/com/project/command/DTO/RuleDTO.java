package com.project.command.DTO;

import java.util.List;

public class RuleDTO {
    private String query;
    private List<String> arguments;
    private boolean negate;

    public RuleDTO(String query, List<String> arguments, boolean negate) {
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
    }

    //Getters and setters


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getArgs() {
        return arguments;
    }

    public void setArgs(List<String> arguments) {
        this.arguments = arguments;
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }
}
