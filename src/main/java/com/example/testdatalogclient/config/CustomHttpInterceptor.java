package com.example.testdatalogclient.config;

import com.example.testdatalogclient.client.DatalogClient;
import com.example.testdatalogclient.utilities.annotations.Traceable;
import feign.Feign;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CustomHttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("CIAONE");
        Method method = ((HandlerMethod) handler).getMethod();
        Annotation annotation =  method.getAnnotation(Traceable.class);
        if(annotation != null){
            URI uri = new URI("http://192.168.1.48:8080/loggataCasuale");
            HttpRequest request2 = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response2 = HttpClient.newBuilder().build().send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println(response2.body());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
