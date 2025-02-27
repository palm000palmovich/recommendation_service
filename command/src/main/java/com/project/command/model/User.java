package com.project.command.model;

import java.util.Objects;
import java.util.UUID;


public class User {

    private UUID id;

    private String nickName;

    private String firstName;

    private String lastName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
