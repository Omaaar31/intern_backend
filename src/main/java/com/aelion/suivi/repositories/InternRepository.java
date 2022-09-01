/**
 * 
 */
package com.aelion.suivi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aelion.suivi.entities.InternEntity;

/**
 * @author Aelion
 *
 */
public interface InternRepository extends CrudRepository<InternEntity, Long> {

	/**
	 * 
	 * DELETE FROM intern WHERE intern.id = 1;
	 * 
	 * SELECT * FROM intern;
	 * 
	 * SELECT * FROM intern WHERE intern.id = 1;
	 * 
	 * INSERT INTO intern (name, first_name, birth_date, email, phone_number,
	 * adress) VALUES ('BOULAHBAL', 'Omar', '1998-04-04', 'o.b@gmail.com',
	 * '05','10');
	 * 
	 * UPDATE intern SET name 'Van Dam', first_name='Jean-Claude',
	 * birth_date='1963-04-15', email='jcvd@aware.com', phone_number='06',
	 * adress='Anvers' WHERE id = 4;
	 * 
	 */

	public List<InternEntity> findByName(String name);

	public List<InternEntity> findByFirstName(String firstName);

	@Query("SELECT i FROM InternEntity i WHERE i.email = ':email'")
	public InternEntity internByMail(String email);

}
