package ru.romanjavist.showcases.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.lang.reflect.Type;
import java.util.Date;

public class Item {


    private int id;

    private int position;
    @NotEmpty(message = "Книга не может быть без названия")
    @Size(min = 2, max = 100, message = "Название книги должно быть длиной от 2 до 100 символов")
    private String name;


    private Type itemType;


    private double price;

    private Date date_create;
    private Date date_edit;

    public Item(){}

    public Item(int position, String name, Type itemType, double price) {
        this.position = position;
        this.name = name;
        this.itemType = itemType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getItemType() {
        return itemType;
    }

    public void setItemType(Type itemType) {
        this.itemType = itemType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
