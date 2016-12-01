package com.pjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
//@ServletComponentScan //暂时不用，一用问题好多,稍后解决
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
