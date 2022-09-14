/**
 * 
 */
package com.aelion.suivi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Aelion
 *
 */
@Entity()
@Table(name = "evalutation_intern")
public class InternEvaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int note;
	private String comment;

	@ManyToOne()
	@JoinColumn(name = "intern_id")
	private InternEntity intern;

	@JoinColumn(name = "skill_id")
	@ManyToOne()
	private SkillsEntity skills;
}
