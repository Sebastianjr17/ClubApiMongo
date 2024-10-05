package com.ClubApiMongo.app.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ClubApiMongo.app.entity.Jugador;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.JugadorRepository;

@RestController
@RequestMapping(value = "/api/jugadores")
public class JugadorController {
    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/")
    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Jugador getJugadorById(@PathVariable String id) {
        return jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
    }

    @PostMapping("/")
    public Jugador saveJugador(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Jugador jugador = mapper.convertValue(body, Jugador.class);
        return jugadorRepository.save(jugador);
    }

    @PutMapping("/{id}")
    public Jugador updateJugador(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Jugador jugador = mapper.convertValue(body, Jugador.class);
        jugador.setId(id);
        return jugadorRepository.save(jugador);
    }

    @DeleteMapping("/{id}")
    public Jugador deleteJugador(@PathVariable String id) {
    	Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
    	jugadorRepository.deleteById(id);
        return jugador;
    }
}

