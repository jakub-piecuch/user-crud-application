package com.kubapiecuch.springbootwithdatabase;

import com.kubapiecuch.springbootwithdatabase.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SpringBootWithDatabaseApplication {
	public static void main(String[] args) {

		SpringApplication.run(SpringBootWithDatabaseApplication.class, args);
	}

}
