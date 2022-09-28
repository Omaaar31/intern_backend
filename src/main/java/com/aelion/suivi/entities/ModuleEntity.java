/**
 * 
 */
package com.aelion.suivi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Aelion
 *
 */

@Entity()
@Table(name = "module")
public class ModuleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;

	@ManyToMany()
	@JoinTable(name = "module_to_poes", joinColumns = @JoinColumn(name = "poe_id"), inverseJoinColumns = @JoinColumn(name = "module_id"))
	private List<POEEntity> poes = new ArrayList<>();

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the poes
	 */
	public List<POEEntity> getPoes() {
		return poes;
	}

	/**
	 * @param poes the poes to set
	 */
	public void setPoes(List<POEEntity> poes) {
		this.poes = poes;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
