package com.dream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * 
 * @author Dileep
 *
 * Application Starting Point. Created by Dileep on 16/05/2019
 * 
 */
@SpringBootApplication
@CrossOrigin("*")
public class MainApp implements ApplicationRunner{

	private static final Logger logger = LogManager.getLogger(MainApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	public void run(ApplicationArguments args) throws Exception {
		logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Warning log");
        logger.error("Error log");
        logger.fatal("Fatal log");
	}
	

	public void newBranch(){
		System.out.println("New Branch");
  }
	public void masterBranch(){
		system.out.println("master branch");
	}
	
}
