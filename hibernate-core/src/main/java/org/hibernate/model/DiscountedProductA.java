/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // ✅ Change to Single Table
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING) // ✅ Add discriminator
@DiscriminatorValue("DISCOUNTED_A") // ✅ Unique identifier for this subclass
@Table(name = "discounted_products_a") // ✅ All subclasses will use the "products" table
public class DiscountedProductA extends Product {

	@Column(precision = 3, scale = 2)
	private BigDecimal discount;

	@Override
	public BigDecimal getPrice() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

//	public void setProduct(Product product) {
//		this.decoratedProduct = product;
//	}
}
