package com.learnreactivespring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class nonReactiveController {

    @GetMapping("/nonreactive")
    public <List>String nonReactiveList(){

        return "Marble, Stone, Diamond";
    }


}
