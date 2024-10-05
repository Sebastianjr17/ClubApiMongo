package com.ClubApiMongo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ClubApiMongo.app.entity.Entrenador;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.EntrenadorRepository;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorTemplateController {
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping("/")
    public String entrenadorListTemplate(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenadores-lista";
    }

    @GetMapping("/new")
    public String entrenadorNewTemplate(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenadores-formulario";
    }

    @GetMapping("/edit/{id}")
    public String entrenadorEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("entrenador", entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("entrenador no encontrado")));
        return "entrenadores-formulario";
    }

    @PostMapping("/save")
    public String entrenadoresSaveProcess(@ModelAttribute("entrenador") Entrenador entrenador) {
        if (entrenador.getId().isEmpty()) {
        	entrenador.setId(null);
        }
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenadores/";
    }

    @GetMapping("/delete/{id}")
    public String entrenadorDeleteProcess(@PathVariable("id") String id) {
    	entrenadorRepository.deleteById(id);
        return "redirect:/entrenadores/";
    }
}
