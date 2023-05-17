package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Controller
public class FooController {

	@PostConstruct
	public void init() {

	}

	@Get("/foo/{id}")
	public String getFoo(@NotNull String id) {
		return "foo_"+id;
	}
}
