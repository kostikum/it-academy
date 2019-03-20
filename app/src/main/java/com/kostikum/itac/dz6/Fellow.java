package com.kostikum.itac.dz6;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Fellow {

    private UUID mUuid;

    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("surname")
    private String mSurname;
    @SerializedName("age")
    private Integer mAge;
    @SerializedName("isDegree")
    private Boolean isDegree;

    public Fellow() {
        this.mUuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return mUuid;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public int getAge() {
        return mAge;
    }

    public boolean isDegree() {
        return isDegree;
    }

    public void setDegree(boolean degree) {
        isDegree = degree;
    }
}
