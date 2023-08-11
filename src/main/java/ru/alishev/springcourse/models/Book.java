package ru.alishev.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    @NotEmpty(message = "Книга не может быть без названия")
    @Size(min = 2, max = 100, message = "Название книги должно быть длиной от 2 до 100 символов")
    private String title;
    @NotEmpty(message = "Книга не может быть без автора")
    @Size(min = 2, max = 50, message = "Имя автора должно быть длиной от 2 до 50 символов")
    private String author;
    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    @Max(value = 2024, message = "Год должен быть меньше, чем 2024")
    private int year;

    public Book(){}
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
