package com.mileworks.authsys;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import com.mileworks.authsys.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.mileworks.authsys.repository"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
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
