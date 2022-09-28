/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.dto.InternInputDTO;
import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.services.InternService;

/**
 * @author Aelion
 *
 */

@RestController
@RequestMapping("/intern")
public class InternController {

	@Autowired
	private InternService internService;

	@GetMapping("/hello")
	// RespnseEntity = class propre à Java
	public ResponseEntity<String> greetings() {
		return ResponseEntity.ok("Hello SpringBoot");
	}

	@GetMapping()
	@CrossOrigin
	public List<InternEntity> getAll() {
		return this.internService.findAll();
	}

	@GetMapping("/shortlist")
	public List<InternShortListDto> shortList() {
		return this.internService.shortList();
	}

	/*
	 * @GetMapping("/shortlist") public List<InternShortListDto> findAll() { return
	 * this.internService.internsAsIterable(); }
	 */

	@GetMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		Optional<InternEntity> optionalInternEntity = this.internService.findOne(id);

		if (optionalInternEntity.isPresent()) {
			return ResponseEntity.ok(optionalInternEntity.get());
		}
		return ResponseEntity.notFound().build(); // Return status http (404)
	}

	@PostMapping()
	@CrossOrigin
	public InternEntity add(@RequestBody InternInputDTO intern) {
		return this.internService.addInternAndPoes(intern);
	};

	@DeleteMapping()
	@CrossOrigin()
	public ResponseEntity<Object> delete(@RequestBody InternInputDTO intern) {
		this.internService.deleteInternAndPoes(intern);
		return ResponseEntity.noContent().build();
	}

	/*
	 * @DeleteMapping("/{id}")
	 * 
	 * @CrossOrigin public ResponseEntity<?> delete(@PathVariable Long id) { try {
	 * this.internService.delete(id); return ResponseEntity.noContent().build(); //
	 * Return status http (204)
	 * 
	 * } catch (NotFoundException e) { return e.send(); } }
	 */

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody InternEntity intern) {
		this.internService.update(intern);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/byname/{name}")
	public List<InternEntity> findByName(@PathVariable String name) {
		return this.internService.findByName(name);
	}

	@GetMapping("/byfirstname/{firstName}")
	public List<InternEntity> findByFirstName(@PathVariable String firstName) {
		return this.internService.findByFirstname(firstName);
	}

	@GetMapping("/byemail")
	@CrossOrigin
	public ResponseEntity<?> byEmail(@RequestParam() String email) {
		return this.internService.byEmail(email);
	}
}