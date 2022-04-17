package com.c4cydonia.applicationproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.c4cydonia.applicationproperties.service.GenericValidator;

@SpringBootApplication
public class ApplicationProperties {

	public static void main(String[] args) {

		var context = SpringApplication.run(ApplicationProperties.class, args);

		System.out.println("hello!");

		var attachmentValidator = context.getBean(GenericValidator.class);
		System.out.println("Welcome: " + attachmentValidator.getWelcome());
		System.out.println("max size: " + attachmentValidator.getMaxSizeLong());
		System.out.println("Allowed formats: " + attachmentValidator.getAllowedFormats());
		System.out.println("Restricted formats: " + attachmentValidator.getRestrictedFormats());

		var size = attachmentValidator.getMaxSizeLong().get("mobile");
		System.out.println("size: " + size);
		System.out.println("size name: " + size.getClass().getName());
		System.out.println("size simpleName: " + size.getClass().getSimpleName());
	}

}
