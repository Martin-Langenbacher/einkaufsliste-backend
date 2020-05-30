package de.martin.learning.einkaufsliste.backend.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {

	// variablen	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	int amount;
	String remark;
	LocalDate lastBought;
	boolean needed;
	
	
	// constructor
	public Item(String name, int amount, String dateString, String remark, boolean needed) {
		this.lastBought = LocalDate.parse(dateString);
		this.amount = amount;
		this.name = name;
		this.remark = remark;
		this.needed = needed;
	}

}

	



	
	