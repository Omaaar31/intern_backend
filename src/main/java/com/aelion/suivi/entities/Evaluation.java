/**
 * 
 */
package com.aelion.suivi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Aelion
 *
 */

@Entity()
@Table(name = "evalutaion")
public class Evaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int note;

	@ManyToOne()
	private ModuleEntity modules;

	/**
	 * @return the modules
	 */

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the note
	 */
	public int getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(int note) {
		this.note = note;
	}

	/**
	 * @return the modules
	 */
	public ModuleEntity getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(ModuleEntity modules) {
		this.modules = modules;
	}
}
