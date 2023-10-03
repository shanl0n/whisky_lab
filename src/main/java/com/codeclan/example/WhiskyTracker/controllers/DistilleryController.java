package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistilleryController {
    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/distilleries")
    public ResponseEntity<List<Distillery>> getDistilleries(@RequestParam(name="region", required = false) String regionGiven){
        if (regionGiven != null){
            return new ResponseEntity<>(distilleryRepository.getByRegion(regionGiven), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/distilleries/ages")
    public ResponseEntity<List<Distillery>> getDistilleryByWhiskyAge(@RequestParam(name="age", required = true)Integer ageGiven){
        return new ResponseEntity<>(distilleryRepository.getByWhiskiesAgeGreaterThanEqual(ageGiven),HttpStatus.IM_USED);
    }


}
