//package ru.romanjavist.showcases.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import ru.romanjavist.showcases.dao.ShowcaseDAO;
//import ru.romanjavist.showcases.models.Showcase;
//
//@Component
//public class PersonValidator implements Validator {
//
//    private final ShowcaseDAO showcaseDAO;
//
//    @Autowired
//    public PersonValidator(ShowcaseDAO showcaseDAO) {
//        this.showcaseDAO = showcaseDAO;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Showcase.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        Showcase showcase = (Showcase) o;
//
//        if (showcaseDAO.getPersonByFullName(showcase.getFullName()).isPresent())
//            errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
//    }
//}
