package org.acme.quarkus.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-hello")
public class HelloController {

	@GetMapping(produces = { "text/plan" })
	public String hello() {
		return "spring => hello";
	}

	@GetMapping(value = "/{name}", produces = { "text/plain" })
	public String helloWithName(@PathVariable(value = "name") String name) {
		return String.format("spring => hello %s", name);
	}

}
