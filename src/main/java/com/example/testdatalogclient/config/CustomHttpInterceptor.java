package com.example.testdatalogclient.config;

import com.example.testdatalogclient.dataTransferObject.TraceableTime;
import com.example.testdatalogclient.utilities.annotations.Traceable;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CustomHttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        Annotation annotation =  method.getAnnotation(Traceable.class);
        if(annotation != null){
            request.setAttribute("startTime", System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        Annotation annotation =  method.getAnnotation(Traceable.class);
        if(annotation != null){
            long startTime = (long) request.getAttribute("startTime");
            URI uri = new URI("http://192.168.1.55:8080/loggataCasuale");
            TraceableTime execTime = new TraceableTime(startTime, System.currentTimeMillis(), method.getName());
            Traceable t = (Traceable) annotation;
            if(t.cpuUsage()){
                OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                double cpuUsage = osBean.getSystemLoadAverage();
                execTime.setCpuUsage(cpuUsage);
            }
            if(t.memoryUsage()){
                OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                double memoryUsage = osBean.getTotalMemorySize();
                execTime.setMemoryUsage(memoryUsage);

            }
            Gson gson = new Gson();
            HttpRequest request2 = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(execTime))).header("Content-Type", "application/json").build();
            HttpResponse<String> response2 = HttpClient.newBuilder().build().send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println(response2.body());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
