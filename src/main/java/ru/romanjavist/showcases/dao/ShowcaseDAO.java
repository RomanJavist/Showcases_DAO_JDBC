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
        jdbcTemplate.update("INSERT INTO Showcase(full_name, year_of_birth) VALUES(?, ?)", showcase.getFullName(), showcase.getYearOfBirth());
    }

    public void update(int id, Showcase updatedShowcase) {
        jdbcTemplate.update("UPDATE Showcase SET full_name=?, year_of_birth=? WHERE id=?", updatedShowcase.getFullName(),
                updatedShowcase.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Showcase WHERE id=?", id);
    }

    // Для валидации уникальности ФИО
    public Optional<Showcase> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Showcase WHERE full_name=?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Showcase.class)).stream().findAny();
    }

    // Здесь Join не нужен. Так как уже получили человека с помощью отдельного метода
    public List<Item> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Item WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Item.class));
    }
}















