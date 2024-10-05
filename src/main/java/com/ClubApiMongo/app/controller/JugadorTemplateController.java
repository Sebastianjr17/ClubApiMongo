package com.ClubApiMongo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ClubApiMongo.app.entity.Jugador;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.JugadorRepository;

@Controller
@RequestMapping("/jugadores")
public class JugadorTemplateController {
    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/")
    public String jugadorListTemplate(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        return "jugadores-lista";
    }

    @GetMapping("/new")
    public String jugadoresNewTemplate(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugadores-formulario";
    }

    @GetMapping("/edit/{id}")
    public String jugadorEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("jugador", jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado")));
        return "jugadores-formulario";
    }

    @PostMapping("/save")
    public String jugadoresSaveProcess(@ModelAttribute("jugador") Jugador jugador) {
        if (jugador.getId().isEmpty()) {
        	jugador.setId(null);
        }
        jugadorRepository.save(jugador);
        return "redirect:/jugadores/";
    }

    @GetMapping("/delete/{id}")
    public String jugadorDeleteProcess(@PathVariable("id") String id) {
    	jugadorRepository.deleteById(id);
        return "redirect:/jugadores/";
    }
}

