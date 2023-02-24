package com.driagon.springboot.datajpa.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvccConfig implements WebMvcConfigurer {

    private Logger log = LoggerFactory.getLogger(MvccConfig.class);

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

        log.info(resourcePath);

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(resourcePath);
    }*/
}