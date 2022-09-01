/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.services.POEService;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Aelion
 *
 */

@RestController
@RequestMapping("/poe")
public class POEController {

	@Autowired
	private POEService poeService;

	public ResponseEntity<String> greetings() {
		return ResponseEntity.ok("Hello SpringBoot");
	}

	@GetMapping()
	public List<POEEntity> getAll() {
		return this.poeService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) throws Exception {
		try {
			return ResponseEntity.ok(this.poeService.getOne((long) id));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return e.send();
		}
	}

	@PostMapping()
	public POEEntity add(@RequestBody POEEntity poe) {
		return this.poeService.add(poe);
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody POEEntity poe) {
		this.poeService.update(poe);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			this.poeService.delete((long) id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("Id was not provided", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}