package org.example.lab_4_jakartaee.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        // Разрешаем все домены или указываем конкретный домен
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

        // Разрешаем методы, которые могут быть использованы для кросс-доменных запросов
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // Разрешаем необходимые заголовки для запросов, например, Content-Type, Authorization
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Разрешаем отправку учетных данных, если это нужно
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");

        // Обрабатываем preflight запросы (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {
            responseContext.setStatus(200); // Возвращаем 200 для preflight запроса
            // Возвращаем необходимые заголовки для preflight запроса
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        }
    }
}
