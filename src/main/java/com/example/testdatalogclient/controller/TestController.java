package com.example.testdatalogclient.controller;

import com.example.testdatalogclient.client.LogItClient;
import com.example.testdatalogclient.client.TYPE;
import com.example.testdatalogclient.utilities.annotations.Traceable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {

    //@Traceable()
    @GetMapping("/test")
    public ResponseEntity<String> testLog() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
        Random r = new Random();

        try {
            LogItClient.log(TYPE.PLAIN, "A", "ciao sono gasato casualmente : "+r.nextInt());
        } catch (Exception e) {
        }
        return ResponseEntity.ok("DONE");
    }

}
