package com.fortran94.bazaweb;

import com.fortran94.bazaweb.service.NotificationService;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@SpringBootApplication
public class BazawebApplication {

	static HttpClient client = HttpClient.newBuilder().build();


	@SneakyThrows
    public static void main(String[] args) {

		SpringApplication.run(BazawebApplication.class, args);

	}

}
