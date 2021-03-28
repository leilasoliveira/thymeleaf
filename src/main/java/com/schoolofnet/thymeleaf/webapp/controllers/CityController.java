package com.schoolofnet.thymeleaf.webapp.controllers;

import com.schoolofnet.thymeleaf.webapp.models.City;
import com.schoolofnet.thymeleaf.webapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("cities", cityRepository.findAll());
        return "cities/index";
    }

    @GetMapping("/new")
    public String newCity(Model model){
        model.addAttribute("city", new City());
        return "cities/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("city") City city, Model model) {
        this.cityRepository.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/{id}")
    public String editCity(@PathVariable("id") Long id, Model model){
        model.addAttribute("city", cityRepository.getOne(id));
        return "cities/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("city") City city, Model model) {
        City findCity = cityRepository.getOne(id);

        if(findCity != null) {
            findCity.setId(city.getId());
            findCity.setNome(city.getNome());
            cityRepository.save(findCity);

            return "redirect:/cities";
        }

        return "redirect:/cities";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id, Model model) {
        City findCity = cityRepository.getOne(id);

        if (findCity != null) {
            cityRepository.delete(findCity);
            return "redirect:/cities";
        }

        return "redirect:/cities";
    }
}
