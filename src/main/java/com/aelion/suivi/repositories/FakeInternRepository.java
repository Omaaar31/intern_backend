/**
 * 
 */
package com.aelion.suivi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aelion.suivi.entities.InternEntity;

/**
 * @author Aelion
 *
 */
public final class FakeInternRepository {
	private List<InternEntity> interns = new ArrayList<>();

	public FakeInternRepository() {
		this.populate();

	};

	public List<InternEntity> getInterns() {
		return this.interns;
	}

	public InternEntity add(InternEntity intern) {
		this.interns.add(intern);
		return intern;
	}

	public void delete(InternEntity intern) {
		this.interns.remove(intern);
	}

	public Optional<InternEntity> findOne(Long id) {
		for (InternEntity intern : this.interns) {
			if (intern.getId() == id) {
				return Optional.of(intern);
			}
		}
		return Optional.empty();
	}

	public void populate() {
		InternEntity intern = new InternEntity();
		intern.setId(1L);
		intern.setName("Aubert");
		intern.setFirstName("Jean-Luc");
		intern.setEmail("jla@gmail.com");
		intern.setPhoneNumber("+(33) 6 23 56 89 14");

		this.interns.add(intern);

		intern = new InternEntity();
		intern.setId(2L);
		intern.setName("Bond");
		intern.setFirstName("James");
		intern.setEmail("bjames@gmail.com");
		intern.setPhoneNumber("07 07 07 07 07");

		this.interns.add(intern);

	}

}
