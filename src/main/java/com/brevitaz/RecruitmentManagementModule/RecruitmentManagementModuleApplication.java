package com.brevitaz.RecruitmentManagementModule;

import com.brevitaz.RecruitmentManagementModule.config.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class RecruitmentManagementModuleApplication {

	@Autowired
	private Environment environment;



	public static void main(String[] args) {

		SpringApplication.run(RecruitmentManagementModuleApplication.class, args);

	}
}