/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "discounted_products_b")
@PrimaryKeyJoinColumn(name = "id")
public class DiscountedProductB extends Product {
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "decorated_product_id", nullable = false)
//    private Product decoratedProduct;

	@Column(precision = 3, scale = 2)
	private BigDecimal discount;

	@Override
	public BigDecimal getPrice() {
		return new BigDecimal(1);
	}
}
