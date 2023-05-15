package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.validation.constraints.NotNull;

@Controller
public class FooController {

	@Get("/foo/{id}")
	public String getFoo(@NotNull String id) {
		return "foo_"+id;
	}
}
