package com.ClubApiMongo.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ClubApiMongo.app.entity.Competicion;
import com.ClubApiMongo.app.exception.NotFoundException;
import com.ClubApiMongo.app.repository.CompeticionRepository;

@Controller
@RequestMapping("/competiciones")
public class CompeticionTemplateController {
    @Autowired
    private CompeticionRepository competicionRepository;

    @GetMapping("/")
    public String competicionListTemplate(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competiciones-lista";
    }

    @GetMapping("/new")
    public String competicionNewTemplate(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competiciones-formulario";
    }

    @GetMapping("/edit/{id}")
    public String competicionEditTemplate(@PathVariable("id") String id, Model model) {
        
    	Competicion competicion = competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrado"));
    	
    	model.addAttribute("competicion",competicion);
        
    	//Asegúrate de que las fechas no sean nulas
        System.out.println("Fecha Inicial: " + competicion.getFechaInicial());
        System.out.println("Fecha Final: " + competicion.getFechaFinal());

        //Asegúrate de que la competición se encuentra en el modelo
        model.addAttribute("competicion", competicion);
        return "competiciones-formulario";
    }

    @PostMapping("/save")
    public String competicionesSaveProcess(@ModelAttribute("competicion") Competicion competicion) {
        if (competicion.getId().isEmpty()) {
        	competicion.setId(null);
        }
        competicionRepository.save(competicion);
        return "redirect:/competiciones/";
    }

    @GetMapping("/delete/{id}")
    public String competicionDeleteProcess(@PathVariable("id") String id) {
    	competicionRepository.deleteById(id);
        return "redirect:/competiciones/";
    }
}
