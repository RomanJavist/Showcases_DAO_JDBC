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
        jdbcTemplate.update("INSERT INTO Item(position, name, type, price) VALUES(?, ?, ?, ?)", item.getPosition(), item.getName(), item.getItemType(), item.getPrice());
    }

    public void update(int id, Item updatedItem) {
        jdbcTemplate.update("UPDATE Item SET position=?, name=?, type=?, price=? WHERE id=?", updatedItem.getPosition(), updatedItem.getName(), updatedItem.getItemType(), updatedItem.getPrice(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Item WHERE id=?", id);
    }

    // Join-им таблицы Item и Showcase и получаем витрину, которой принадлежит товар с указанным id
    public Optional<Showcase> getItemOwner(int id) {
        // Выбираем все колонки таблицы Showcase из объединенной таблицы
        return jdbcTemplate.query("SELECT Showcase.* FROM Item JOIN Showcase ON Item.showcase_id = Showcase.id WHERE Item.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Showcase.class)).stream().findAny();
    }

    // Освобождает товар (этот метод вызывается, когда витрина возвращает товар на склад)
    public void release(int id) {
        jdbcTemplate.update("UPDATE Item SET showcase_id = NULL WHERE id = ?", id);
    }

    // Назначает товар витрине (этот метод вызывается, когда витрина забирает товар со склада)
    public void assign(int id, Showcase selectedShowcase) {
        jdbcTemplate.update("UPDATE Item SET showcase_id=? WHERE id=?", selectedShowcase.getId(), id);
    }
}
