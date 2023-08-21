package ru.romanjavist.showcases.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.romanjavist.showcases.models.Item;
import ru.romanjavist.showcases.models.Showcase;

import java.util.List;
import java.util.Optional;

@Component
public class ItemDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> index() {
        return jdbcTemplate.query("SELECT * FROM Item", new BeanPropertyRowMapper<>(Item.class));
    }

    public Item show(int id) {
        return jdbcTemplate.query("SELECT * FROM Item WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Item.class))
                .stream().findAny().orElse(null);
    }

    public void save(Item item) {
        jdbcTemplate.update("INSERT INTO Item(title, author, year) VALUES(?, ?, ?)", item.getTitle(), item.getAuthor(), item.getYear());
    }

    public void update(int id, Item updatedItem) {
        jdbcTemplate.update("UPDATE Item SET title=?, author=?, year=? WHERE id=?", updatedItem.getTitle(), updatedItem.getAuthor(), updatedItem.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    // Join-им таблицы Book и Person и получаем человека, которому принадлежит книга с указанным id
    public Optional<Showcase> getBookOwner(int id) {
        // Выбираем все колонки таблицы Person из объединенной таблицы
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Showcase.class)).stream().findAny();
    }

    // Освобождает книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE id = ?", id);
    }

    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    public void assign(int id, Showcase selectedShowcase) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedShowcase.getId(), id);
    }
}
