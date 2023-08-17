package com.epam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym")
public class GymController {

    @GetMapping("/test")
    public String test(){
        return "Gym-Application works";
    }
}
