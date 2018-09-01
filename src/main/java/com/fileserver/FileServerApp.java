package com.fileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fileserver.config.FileProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileProperties.class
})
public class FileServerApp {

	
	
	public static void main(String[] args) {
		SpringApplication.run(FileServerApp.class, args);
	}

	
	
	
	
	

}
