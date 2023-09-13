package com.prodapt.bicyclespring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodapt.bicyclespring.entity.Bicycle;
import com.prodapt.bicyclespring.model.BicycleService;
import com.prodapt.bicyclespring.repository.CycleRepository;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bicycles")
public class BicycleController {

    @Autowired
    private CycleRepository bicycleRepository;

    @GetMapping
    public ResponseEntity<List<Bicycle>> listBicycles(Model model) {
        List<Bicycle> bicycles = (List<Bicycle>)bicycleRepository.findAll();
        model.addAttribute("bicycles", bicycles);
        model.addAttribute("bicycle", new Bicycle());
        
        return new ResponseEntity<>(bicycles,HttpStatus.OK); // Thymeleaf template for listing bicycles
    }
    
    @PostMapping("/borrow")
    public ResponseEntity<String> borrow(@RequestParam int id) {
    	
    	Bicycle bicycle = bicycleRepository.findById((long) id).orElse(null);
    	if (bicycle != null && bicycle.getStock() > 0) {
            // Decrease the stock by 1
            bicycle.setStock(bicycle.getStock() - 1);
            bicycleRepository.save(bicycle);
        }

        return new ResponseEntity<>("Borrow is done",HttpStatus.CREATED);
    	
    }

    @GetMapping("/add")
    public ResponseEntity<String> showAddBicycleForm(Model model ) {
        model.addAttribute("bicycle", new Bicycle());
        return new ResponseEntity<>("Cycle is added",HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBicycle(@RequestBody @Valid  Bicycle bicycle,BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		
    	}
    	else
    		bicycleRepository.save(bicycle);
        return new ResponseEntity<>("Cycle is added",HttpStatus.CREATED);
    }
    @PostMapping("/restock")
    public ResponseEntity<String> restockBicycle(
            @RequestParam Long id,
            @RequestParam Integer quantity
    ) {
        // Retrieve the bicycle by its ID
        Bicycle bicycle = bicycleRepository.findById(id).orElse(null);

        if (bicycle != null) {
            // Increase the stock by the specified quantity
            bicycle.setStock(bicycle.getStock() + quantity);
            bicycleRepository.save(bicycle);
        }

        return new ResponseEntity<>("Cycle is added",HttpStatus.CREATED);
    }
    
    @PostMapping("/return")
    public ResponseEntity<String>returnBicycle(@RequestParam int id) {
        Bicycle bicycle = bicycleRepository.findById((long) id).orElse(null);
        if (bicycle != null) {
            // Increase the stock by 1 when returning a cycle
            bicycle.setStock(bicycle.getStock() + 1);
            bicycleRepository.save(bicycle);
        }

        return new ResponseEntity<>("Cycle is returned",HttpStatus.CREATED);
    }
    
    
}
