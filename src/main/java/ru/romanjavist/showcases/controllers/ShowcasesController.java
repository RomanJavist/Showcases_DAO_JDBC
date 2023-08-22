package ru.romanjavist.showcases.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.romanjavist.showcases.dao.ShowcaseDAO;
import ru.romanjavist.showcases.models.Showcase;

import javax.validation.Valid;

@Controller
@RequestMapping("/showcases")
public class ShowcasesController {

    private final ShowcaseDAO showcaseDAO;

    @Autowired
    public ShowcasesController(ShowcaseDAO showcaseDAO) {
        this.showcaseDAO = showcaseDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("showcases", showcaseDAO.index());
        return "showcases/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("showcase", showcaseDAO.show(id));
        model.addAttribute("items", showcaseDAO.getBooksByPersonId(id));
        return "showcases/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("showcase") Showcase showcase) {
        return "showcases/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("showcase") @Valid Showcase showcase,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "showcases/new";

        showcaseDAO.save(showcase);
        return "redirect:/showcases";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("showcase", showcaseDAO.show(id));
        return "showcases/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("showcase") @Valid Showcase showcase, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "showcases/edit";

        showcaseDAO.update(id, showcase);
        return "redirect:/showcases";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        showcaseDAO.delete(id);
        return "redirect:/showcases";
    }
}
