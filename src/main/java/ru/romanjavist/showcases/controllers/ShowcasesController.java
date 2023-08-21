package ru.romanjavist.showcases.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.romanjavist.showcases.dao.ShowcaseDAO;
import ru.romanjavist.showcases.models.Showcase;
import ru.romanjavist.showcases.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class ShowcasesController {

    private final ShowcaseDAO showcaseDAO;

    @Autowired
    public ShowcasesController(ShowcaseDAO showcaseDAO) {
        this.showcaseDAO = showcaseDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", showcaseDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", showcaseDAO.show(id));
        model.addAttribute("books", showcaseDAO.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Showcase showcase) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Showcase showcase,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        showcaseDAO.save(showcase);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", showcaseDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Showcase showcase, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        showcaseDAO.update(id, showcase);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        showcaseDAO.delete(id);
        return "redirect:/people";
    }
}
