package ru.romanjavist.showcases.models;

import javax.validation.constraints.*;
import java.lang.reflect.Type;
import java.util.Date;

public class Showcase {

    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть длиной от 2 до 100 символов")
    private String name;

    @NotEmpty(message = "Адрес не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть длиной от 2 до 100 символов")
    private String address;

    private Type showcaseType;

    private Date date_create;
    private Date date_edit;

    public Showcase() {
    }

    public Showcase(String name, String address, Type showcaseType) {
        this.name = name;
        this.address = address;
        this.showcaseType = showcaseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Type getShowcaseType() {
        return showcaseType;
    }

    public void setShowcaseType(Type showcaseType) {
        this.showcaseType = showcaseType;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public Date getDate_edit() {
        return date_edit;
    }

    public void setDate_edit(Date date_edit) {
        this.date_edit = date_edit;
    }
}
