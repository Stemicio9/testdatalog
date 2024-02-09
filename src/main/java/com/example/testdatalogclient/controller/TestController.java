package com.example.testdatalogclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> testLog(){
        for(int i=0;i<10; i++)
            System.out.println("Ciao "+i);
        return ResponseEntity.ok("DONE");
    }

}
