package com.jm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@SequenceGenerator(name="product_seq_gen", sequenceName="product_id_gen", allocationSize=1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{
	
	@Id
	@GeneratedValue(generator="product_seq_gen")
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	
}
