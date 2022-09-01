/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.POERepository;

/**
 * @author Aelion
 *
 */

@Service
public class POEService implements ICrud<POEEntity> {

	@Autowired
	private POERepository repository;

	@Override
	public POEEntity add(POEEntity t) {
		return this.repository.save(t);
	}

	@Override
	public POEEntity[] add(POEEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(POEEntity t) {
		this.repository.save(t);

	}

	@Override
	public void delete(POEEntity t) {
		this.repository.delete(t);
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<POEEntity> pEntity = this.findOne(id);
		if (pEntity.isPresent()) {
			try {
				this.repository.deleteById(id.intValue());
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException();
			}
		} else {
			throw new Exception("POE with id : " + id + " was not found !");
		}
	}

	@Override
	public List<POEEntity> findAll() {
		return (List<POEEntity>) this.repository.findAll();
	}

	@Override
	public Optional<POEEntity> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.repository.findById(id.intValue());
	}

	public POEEntity getOne(Long id) throws Exception {
		Optional<POEEntity> oEntity = this.repository.findById(id.intValue());

		if (oEntity.isPresent()) {
			return oEntity.get();
		}
		throw new Exception("the POE with " + id + " not found");
	}
	/*
	 * public void populate() { POEEntity poe = new POEEntity(); poe.setId(1);
	 * poe.setName("Java"); poe.setBeginDate(2022-07-18);
	 * poe.setEndDate("2022-10-19");
	 * 
	 * this.poe.add(poe);
	 * 
	 * poe = new POEEntity(); poe.setId(2L); poe.setName("Bond");
	 * poe.setFirstName("James"); poe.setEmail("bjames@gmail.com");
	 * 
	 * this.poe.add(poe);
	 * 
	 * }
	 */

}
