package com.dotin.dotintasktwo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DotinTaskTwoApplication.class);
    }

}
