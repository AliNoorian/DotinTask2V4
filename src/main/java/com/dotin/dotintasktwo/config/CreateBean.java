package com.dotin.dotintasktwo.config;

import com.dotin.dotintasktwo.model.*;
import com.dotin.dotintasktwo.utility.DateUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBean {

    @Bean
    public Category getCategory() {
        return new Category();
    }

    @Bean
    public CategoryElement getCategoryElement() {
        return new CategoryElement();
    }

    @Bean
    public Email getEmail() {
        return new Email();
    }

    @Bean
    public Employee getEmployee() {
        return new Employee();
    }

    @Bean
    public Leave getLeave() {
        return new Leave();
    }





}
