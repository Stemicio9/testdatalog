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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            LogItClient.log( TYPE.PLAIN,"d","entera");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            try {
                Date parsedDate = dateFormat.parse("2024-02-14 13:08:22.886");
                System.out.println(parsedDate);
                LogItClient.clear("d",parsedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("DONE");
    }
}
