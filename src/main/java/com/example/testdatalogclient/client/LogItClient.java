package com.example.testdatalogclient.client;


import com.example.testdatalogclient.dataTransferObject.ClearData;
import com.example.testdatalogclient.dataTransferObject.LogIt;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

public class LogItClient {
    //aggiunge un log del tipo specificato
    public static boolean log(TYPE type, String category, String msg) throws URISyntaxException, IOException, InterruptedException {
        Date date = new Date();
        System.out.println(date);

        if (type == TYPE.PLAIN) {
            URI uri = new URI("http://192.168.1.55:8081/logEsplicito/plain");
            LogIt li = new LogIt(category, msg,date.getTime());
            Gson gson = new Gson();

            HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(li))).header("Content-Type", "application/json").build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }



        return true;
    }
    //cancella tutti i log di una categoria
    public static boolean clear (String category) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("http://192.168.1.55:8081/clear");
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(category))).header("Content-Type", "application/json").build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return true;
    }

    public static boolean clear(String category, Date data) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("http://192.168.1.5x5:8081/clear/beforeData");
        long d = data.getTime();
        ClearData cd = new ClearData(category,d);
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(cd))).header("Content-Type", "application/json").build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return true;
    }

}
