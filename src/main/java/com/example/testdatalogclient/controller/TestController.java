package com.example.testdatalogclient.controller;

import com.example.testdatalogclient.client.LogItClient;
import com.example.testdatalogclient.client.TYPE;
import com.example.testdatalogclient.dataTransferObject.LogIt;
import com.example.testdatalogclient.utilities.annotations.Traceable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
public class TestController {


    @GetMapping("/test")
    public ResponseEntity<String> testLog(){
        for(int i=0;i<10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            LogItClient.log( TYPE.PLAIN,"d","entra");
            LogItClient.clear("c",new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("DONE");
    }
}
