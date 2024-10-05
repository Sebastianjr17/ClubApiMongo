package com.ClubApiMongo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ClubApiMongo.app.entity.Asociacion;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.AsociacionRepository;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionTemplateController {

	@Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping("/")
    public String asociacionListTemplate(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "asociaciones-lista";
    }

    @GetMapping("/new")
    public String asociacionNewTemplate(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociaciones-formulario";
    }

    @GetMapping("/edit/{id}")
    public String asociacionEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("asociacion", asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociación no encontrado")));
        return "asociaciones-formulario";
    }

    @PostMapping("/save")
    public String asociacionesSaveProcess(@ModelAttribute("asociacion") Asociacion asociacion) {
        if (asociacion.getId().isEmpty()) {
        	asociacion.setId(null);
        }
        asociacionRepository.save(asociacion);
        return "redirect:/asociaciones/";
    }

    @GetMapping("/delete/{id}")
    public String asociacionDeleteProcess(@PathVariable("id") String id) {
    	asociacionRepository.deleteById(id);
        return "redirect:/asociaciones/";
    }
}
