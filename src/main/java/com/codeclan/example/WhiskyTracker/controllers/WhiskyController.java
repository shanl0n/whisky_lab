package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(@RequestParam(name = "year", required = false) Integer chosenYear){
        if (chosenYear != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(chosenYear), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);

    }
    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getOneWhisky(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distage")
    public ResponseEntity<List<Whisky>> getByDistilleryAndAge(@RequestParam(name = "d") String distilleryName,
                                                                @RequestParam(name = "a") Integer chosenAge){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distilleryName,chosenAge), HttpStatus.I_AM_A_TEAPOT);
    }
    @GetMapping(value="/whiskies/regional")
    public ResponseEntity<List<Whisky>> gerAllInRegion(@RequestParam(name = "region")String regionGiven){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(regionGiven), HttpStatus.I_AM_A_TEAPOT);
    }



}
