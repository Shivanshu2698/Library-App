package com.lms.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Library Management System API Documentation",
				description = "This API provides all the necessary CRUD operations for managing a library, including managing books and authors.",
				summary = "This project performs all CRUD operations on Book and Author entities, allowing users to manage a library's inventory efficiently.",
				termsOfService = "T&C",
				contact = @Contact(
						name = "Shivanshu Shukla",
						email = "shuklashivanshu79@gmail.com"
				),
				license = @License(
						name = "License Number..........."
				),
				version = "1.0.0"
				
				)
		)


public class OpenAiConfig {

}

