package com.example.testdatalogclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name="datalog-client")
public interface DatalogClient{
    
    @RequestMapping(method= GET, value= "/loggataCasuale")
    public ResponseEntity<String> loggamiStoTrapezio(@RequestParam String input);
    
}
