package com.example.testdatalogclient.controller;

import com.example.testdatalogclient.utilities.annotations.Traceable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Traceable()
    @GetMapping("/test")
    public ResponseEntity<String> testLog() {
        for(int i=0;i<10; i++){
            try {
                Thread.sleep(1000);
            }catch(Exception e){}
        }
        return ResponseEntity.ok("DONE");
    }
}
