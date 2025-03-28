package com.project.command.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InfoService {
    @Value("${spring.application.name}")
    private String name;

    @Value("${project.version}")
    private String version;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
