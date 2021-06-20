package com.spring.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_item")
@SequenceGenerator(name = ProductItem.SEQUENCE_GENERATOR, sequenceName = "product_item_id_seq", allocationSize = 1)
public class ProductItem {
	public static final String SEQUENCE_GENERATOR = "product_item_id_generator";

	@Id
	@GeneratedValue(generator = SEQUENCE_GENERATOR, strategy = SEQUENCE)
	private Long id;
	
	private String title;

}
