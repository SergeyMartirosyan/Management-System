package com.example.demo.LighterConfig;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("api/v1/lighter")
public class LighterController {

    @Autowired
    private LighterService service;

    @GetMapping
    public ResponseEntity<List<Lighter>> getAllLighters(){
        return new ResponseEntity<List<Lighter>>(service.getALL(), HttpStatus.OK);
    }

	// @GetMapping("/{name}")
	// public ResponseEntity<Optional<Lighter>> getLighterbyName(@PathVariable String name){
	// 	return new ResponseEntity<Optional<Lighter>>(service.getLighterbyName(name), HttpStatus.OK);
	// }

	@GetMapping("/{id}")
	public ResponseEntity<Lighter> getLighterbyId(@PathVariable UUID id){
		return new ResponseEntity<Lighter>(service.getLighterbyId(id), HttpStatus.OK);
	}


	@PostMapping
	public HttpStatus insertLighter(@RequestBody Lighter lighter){
        service.insertLighter(lighter);    
		return HttpStatus.CREATED;
    }

	@PutMapping("/{id}")
	public HttpStatus updateLighter(@PathVariable("id") UUID id, @RequestBody Lighter lighter){
		service.updateLighter(id, lighter);
		return HttpStatus.OK;
	}

	@DeleteMapping("/{id}")
	public HttpStatus removeLighter(@PathVariable("id") UUID id){
		service.removeLighter(id);
		return HttpStatus.OK;
	}

    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(false)
                        .maxAge(3600);
			}
		};
	}
}
