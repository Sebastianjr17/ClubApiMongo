package com.ClubApiMongo.app.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ClubApiMongo.app.entity.Asociacion;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.AsociacionRepository;

@RestController
@RequestMapping(value = "/api/asociaciones")
public class AsociacionController {

	@Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping("/")
    public List<Asociacion> getAllAsociaciones() {
        return asociacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Asociacion getAsociacionById(@PathVariable String id) {
        return asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociación no encontrado"));
    }

    @PostMapping("/")
    public Asociacion saveAsociacion(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
        return asociacionRepository.save(asociacion);
    }

    @PutMapping("/{id}")
    public Asociacion updateAsociacion(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
        asociacion.setId(id);
        return asociacionRepository.save(asociacion);
    }

    @DeleteMapping("/{id}")
    public Asociacion deleteAsociacion(@PathVariable String id) {
    	Asociacion asociacion = asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrado"));
    	asociacionRepository.deleteById(id);
        return asociacion;
    }
}
