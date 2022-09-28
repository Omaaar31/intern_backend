/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternInputDTO;
import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.InternRepository;
import com.aelion.suivi.repositories.POERepository;

/**
 * @author Aelion
 *
 */

@Service
public class InternService implements ICrud<InternEntity> {

	@Autowired
	private InternRepository repository;

	@Autowired
	private POERepository poeRepository;

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
	public ResponseEntity<?> delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
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

	public List<InternEntity> findByName(String name) {
		return this.repository.findByName(name);
	}

	public List<InternEntity> findByFirstname(String firstname) {
		return this.repository.findByFirstName(firstname);
	}

	public ResponseEntity<?> byEmail(String email) {
		ResponseEntity response = null;
		InternEntity entity = this.repository.internByMail(email);
		if (entity == null) {
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}

	public boolean emailExists(String email) {
		return this.repository.internByMail(email) == null ? false : true;
	}

	public InternEntity addInternAndPoes(InternInputDTO internDto) {
		InternEntity intern = new InternEntity();
		intern.setAdress(internDto.adress);
		intern.setBirthDate(internDto.birthDate);
		intern.setEmail(internDto.email);
		intern.setFirstName(internDto.firstName);
		intern.setName(internDto.name);
		intern.setPhoneNumber(internDto.phoneNumber);

		this.repository.save(intern);
		// Persists POEs with the new Intern
		internDto.poes.forEach(inputPoe -> {
			Optional<POEEntity> oPoe = this.poeRepository.findById(inputPoe.getId());
			if (oPoe.isPresent()) {
				POEEntity poe = oPoe.get();
				poe.addIntern(intern);
				this.poeRepository.save(poe);
			}
		});
		return intern;

	}

	public void deleteInternAndPoes(InternInputDTO internDto) {
		Optional<InternEntity> intern = this.repository.findById(internDto.id);
		List<POEEntity> poes = (List<POEEntity>) this.poeRepository.findAll();

		// first check if intern is present
		if (intern.isPresent()) {
			// delete POE
			poes.forEach(inputPoe -> {
				inputPoe.deleteIntern(intern.get());
				this.poeRepository.save(inputPoe);
			});
		}

		// delete intern
		this.repository.deleteById(internDto.id);
	}

}
