package com.abhi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.abhi.beans","org.springframework.samples.petclinic.web","org.springframework.samples.petclinic.service",
		"org.springframework.samples.petclinic.repository"})
public class MyConfig {

	
}
