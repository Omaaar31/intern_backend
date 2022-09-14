/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.repositories.InternRepository;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Aelion
 *
 */

@Service
public class InternService implements ICrud<InternEntity> {

	@Autowired
	private InternRepository repository;

	@Override
	public InternEntity add(InternEntity t) {
		// TODO Auto-generated method stub
		return this.repository.save(t);
	}

	@Override
	public InternEntity[] add(InternEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(InternEntity t) {
		this.repository.save(t);
	}

	@Override
	public void delete(InternEntity t) {
		this.repository.delete(t);
	}

	@Override
	public List<InternEntity> findAll() {
		return (List<InternEntity>) this.repository.findAll();
	}

	public List<InternShortListDto> shortList() {

		ArrayList<InternEntity> itEntity = (ArrayList<InternEntity>) this.repository.findAll();
		ArrayList<InternShortListDto> dto = new ArrayList<>();
		for (InternEntity entity : itEntity) {
			InternShortListDto transformed = new InternShortListDto();
			dto.add(transformed.transform(entity));
		}
		return dto;
	}

	@Override
	public Optional<InternEntity> findOne(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public ResponseEntity<?> delete(Long id) throws NotFoundException {
		try {
			this.repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public List<InternEntity> findByName(String name) {
		return this.repository.findByName(name);
	}

	public List<InternEntity> findByFirstname(String firstname) {
		return this.repository.findByFirstName(firstname);
	}

	public InternEntity byEmail(String email) {
		return this.repository.internByMail(email);
	}

	public boolean emailExists(String email) {
		return this.repository.internByMail(email) == null ? false : true;
	}
}
