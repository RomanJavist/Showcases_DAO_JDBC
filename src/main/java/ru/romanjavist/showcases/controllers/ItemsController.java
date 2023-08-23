package ru.romanjavist.showcases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.romanjavist.showcases.dao.ItemDAO;
import ru.romanjavist.showcases.dao.ShowcaseDAO;
import ru.romanjavist.showcases.models.Item;
import ru.romanjavist.showcases.models.Showcase;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private final ItemDAO itemDAO;
    private final ShowcaseDAO showcaseDAO;

    public ItemsController(ItemDAO itemDAO, ShowcaseDAO showcaseDAO) {
        this.itemDAO = itemDAO;
        this.showcaseDAO = showcaseDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("items", itemDAO.index());
        return "items/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Showcase showcase) {
        model.addAttribute("item", itemDAO.show(id));

        Optional<Showcase> itemOwner = itemDAO.getItemOwner(id);

        if (itemOwner.isPresent())
            model.addAttribute("owner", itemOwner.get());
        else
            model.addAttribute("showcases", showcaseDAO.index());

        return "items/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("item") Item item) {
        return "items/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("item") @Valid Item item,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "items/new";

        itemDAO.save(item);
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("item", itemDAO.show(id));
        return "items/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "items/edit";

        itemDAO.update(id, item);
        return "redirect:/items";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        itemDAO.delete(id);
        return "redirect:/items";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        itemDAO.release(id);
        return "redirect:/items/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("showcases") Showcase selectedShowcase) {
        itemDAO.assign(id, selectedShowcase);
        return "redirect:/items/" + id;
    }

}

















