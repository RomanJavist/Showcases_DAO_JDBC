package ru.romanjavist.showcases.models;

import javax.validation.constraints.*;

public class Showcase {
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть длиной от 2 до 100 символов")
    private String fullName;

    @Min(value = 1925, message = "Год рождения должен быть больше, чем 1925")
    @Max(value = 2005, message = "Год рождения должен быть меньше, чем 2005")
    private int yearOfBirth;

    public Showcase() {
    }

    public Showcase(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
