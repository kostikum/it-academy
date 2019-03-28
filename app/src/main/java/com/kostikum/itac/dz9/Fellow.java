package com.kostikum.itac.dz9;

import java.util.UUID;

public class Fellow {

    private UUID uuid;

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Boolean isDegree;

    public Fellow() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isDegree() {
        return isDegree;
    }

    public void setDegree(boolean degree) {
        isDegree = degree;
    }
}
