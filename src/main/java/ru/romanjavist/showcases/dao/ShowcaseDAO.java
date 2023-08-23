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
public class ShowcaseDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShowcaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Showcase> index() {
        return jdbcTemplate.query("SELECT * FROM Showcase", new BeanPropertyRowMapper<>(Showcase.class));
    }

    public Showcase show(int id) {
        return jdbcTemplate.query("SELECT * FROM Showcase WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Showcase.class))
                .stream().findAny().orElse(null);
    }

    public void save(Showcase showcase) {
        jdbcTemplate.update("INSERT INTO Showcase(name, address, type) VALUES(?, ?, ?)", showcase.getName(), showcase.getAddress(), showcase.getShowcaseType());
    }

    public void update(int id, Showcase updatedShowcase) {
        jdbcTemplate.update("UPDATE Showcase SET name=?, address=?, type=? WHERE id=?", updatedShowcase.getName(),
                updatedShowcase.getAddress(), updatedShowcase.getShowcaseType(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Showcase WHERE id=?", id);
    }

    // Для валидации уникальности ФИО
    public Optional<Showcase> getPersonByFullName(String name) {
        return jdbcTemplate.query("SELECT * FROM Showcase WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Showcase.class)).stream().findAny();
    }

    // Здесь Join не нужен. Так как уже получили витрину с помощью отдельного метода
    public List<Item> getBooksByShowcaseId(int id) {
        return jdbcTemplate.query("SELECT * FROM Item WHERE showcase_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Item.class));
    }
}















