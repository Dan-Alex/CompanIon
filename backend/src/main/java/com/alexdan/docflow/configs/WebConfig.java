package com.alexdan.docflow.configs;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerCastomizer(){

        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
        };
    }
}