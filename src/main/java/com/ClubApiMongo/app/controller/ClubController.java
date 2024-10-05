package com.ClubApiMongo.app.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ClubApiMongo.app.entity.Club;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.ClubRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/api/clubes")
public class ClubController {
	
    @Autowired
    private ClubRepository clubRepository;
    

    @GetMapping("/")
    public List<Club> getAllClubes() {
        return clubRepository.findAll();
    }

    @GetMapping("/{id}")
    public Club getClubById(@PathVariable String id) {
        return clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
    }

    @PostMapping("/")
    public Club saveClub(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Club club = mapper.convertValue(body, Club.class);
        return clubRepository.save(club);
    }

    @PutMapping("/{id}")
    public Club updateClub(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Club club = mapper.convertValue(body, Club.class);
        club.setId(id);
        return clubRepository.save(club);
    }

    @DeleteMapping("/{id}")
    public Club deleteClub(@PathVariable String id) {
    	Club club = clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
    	clubRepository.deleteById(id);
        return club;
    }
    
}
