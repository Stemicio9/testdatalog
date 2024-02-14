package com.example.testdatalogclient.client;


import com.example.testdatalogclient.dataTransferObject.LogIt;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LogItClient {

    public static boolean log(TYPE type, String category, String msg) throws URISyntaxException, IOException, InterruptedException {


        if (type == TYPE.PLAIN) {
            URI uri = new URI("http://192.168.1.55:8080/logEsplicito");
            LogIt li = new LogIt(category, msg);
            Gson gson = new Gson();

            HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(li))).header("Content-Type", "application/json").build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }


        return true;
    }

}
