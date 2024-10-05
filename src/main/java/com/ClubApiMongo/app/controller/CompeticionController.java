package com.ClubApiMongo.app.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ClubApiMongo.app.entity.Competicion;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.CompeticionRepository;

@RestController
@RequestMapping(value = "/api/competiciones")
public class CompeticionController {
    @Autowired
    private CompeticionRepository competicionRepository;

    @GetMapping("/")
    public List<Competicion> getAllCompeticiones() {
        return competicionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Competicion getCompeticionById(@PathVariable String id) {
        return competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrado"));
    }

    @PostMapping("/")
    public Competicion saveCompeticion(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Competicion competicion = mapper.convertValue(body, Competicion.class);
        return competicionRepository.save(competicion);
    }

    @PutMapping("/{id}")
    public Competicion updateCompeticion(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Competicion competicion = mapper.convertValue(body, Competicion.class);
        competicion.setId(id);
        return competicionRepository.save(competicion);
    }

    @DeleteMapping("/{id}")
    public Competicion deleteCompeticion(@PathVariable String id) {
    	Competicion competicion = competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrado"));
    	competicionRepository.deleteById(id);
        return competicion;
    }
}
