package com.mileworks.authsys;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		MileWorksAuthApplication.class,
		Jsr310JpaConverters.class
})
public class MileWorksAuthApplication {
	
	//在不更改机器时区的情况下改变一个特定应用程序的时区
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MileWorksAuthApplication.class, args);
	}
}
