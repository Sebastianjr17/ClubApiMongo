package com.ClubApiMongo.app.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ClubApiMongo.app.entity.Entrenador;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.EntrenadorRepository;

@RestController
@RequestMapping(value = "/api/entrenadores")
public class EntrenadorController {
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping("/")
    public List<Entrenador> getAllEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Entrenador getEntrenadorById(@PathVariable String id) {
        return entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Director no encontrado"));
    }

    @PostMapping("/")
    public Entrenador saveEntrenador(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Entrenador entrenador = mapper.convertValue(body, Entrenador.class);
        return entrenadorRepository.save(entrenador);
    }

    @PutMapping("/{id}")
    public Entrenador updateEntrenador(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Entrenador entrenador = mapper.convertValue(body, Entrenador.class);
        entrenador.setId(id);
        return entrenadorRepository.save(entrenador);
    }

    @DeleteMapping("/{id}")
    public Entrenador deleteEntrenador(@PathVariable String id) {
    	Entrenador entrenador = entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Director no encontrado"));
    	entrenadorRepository.deleteById(id);
        return entrenador;
    }
}
