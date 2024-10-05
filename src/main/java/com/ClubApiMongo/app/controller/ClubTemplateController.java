package com.ClubApiMongo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ClubApiMongo.app.entity.*;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.AsociacionRepository;
import com.ClubApiMongo.app.repository.ClubRepository;
import com.ClubApiMongo.app.repository.CompeticionRepository;
import com.ClubApiMongo.app.repository.EntrenadorRepository;
import com.ClubApiMongo.app.repository.JugadorRepository;


@Controller
@RequestMapping("/clubes")
public class ClubTemplateController {
	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private AsociacionRepository asociacionRepository;

	@Autowired
	private EntrenadorRepository entrenadorRepository;

	@Autowired
	private JugadorRepository jugadorRepository;

	@Autowired
	private CompeticionRepository competicionRepository;

	@GetMapping("/")
	public String clubListTemplate(Model model) {
		model.addAttribute("clubes", clubRepository.findAll());
		return "clubes-lista";
	}

	@GetMapping("/new")
	public String clubNewTemplate(Model model, Model asociacionModel, Model entrenadorModel, Model jugadorModel,
			Model competicionModel) {
		List<Asociacion> asociaciones = asociacionRepository.findAll();
		List<Entrenador> entrenadores = entrenadorRepository.findAll();
		List<Jugador> jugadoresDisponibles = jugadorRepository.findAll();
		List<Competicion> competicionesDisponibles = competicionRepository.findAll();

		asociacionModel.addAttribute("asociaciones", asociaciones);
		entrenadorModel.addAttribute("entrenadores", entrenadores);
		model.addAttribute("jugadoresDisponibles", jugadoresDisponibles);
		competicionModel.addAttribute("competicionesDisponibles", competicionesDisponibles);
		model.addAttribute("club", new Club());
		return "clubes-formulario";
	}

	@GetMapping("/edit/{id}")
	public String clubEditTemplate(@PathVariable("id") String id, Model model, Model asociacionModel, Model entrenadorModel, Model jugadorModel,
			Model competicionModel) {
		model.addAttribute("club",clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado")));
		List<Asociacion> asociaciones = asociacionRepository.findAll();
		List<Entrenador> entrenadores = entrenadorRepository.findAll();
		List<Jugador> jugadoresDisponibles = jugadorRepository.findAll();
		List<Competicion> competicionesDisponibles = competicionRepository.findAll();

		asociacionModel.addAttribute("asociaciones", asociaciones);
		entrenadorModel.addAttribute("entrenadores", entrenadores);
		model.addAttribute("jugadoresDisponibles", jugadoresDisponibles);
		competicionModel.addAttribute("competicionesDisponibles", competicionesDisponibles);
		return "clubes-formulario";
	}

	@PostMapping("/save")
	public String clubesSaveProcess(@ModelAttribute("club") Club club) {
		if (club.getId().isEmpty()) {
			club.setId(null);
		}
		clubRepository.save(club);
		return "redirect:/clubes/";
	}

	@GetMapping("/delete/{id}")
	public String clubDeleteProcess(@PathVariable("id") String id) {
		clubRepository.deleteById(id);
		return "redirect:/clubes/";
	}

}
