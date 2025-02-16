/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "basic_products")
@PrimaryKeyJoinColumn(name = "product_id")
public class BasicProduct extends Product {
	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
