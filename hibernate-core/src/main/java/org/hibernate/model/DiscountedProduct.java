/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.Delta;


import java.math.BigDecimal;

@Entity
@Table(name = "discounted_products")
@PrimaryKeyJoinColumn(name = "id")
@Delta(core = "Product.class")
public class DiscountedProduct extends Product implements ProductDecorator {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "decorated_product_id", nullable = false)
	private Product decoratedProduct;

	@Column(precision = 3, scale = 2)
	private BigDecimal discount;

	@Override
	public Product getDecoratedProduct() {
		return decoratedProduct;
	}

	@Override
	public void setDecoratedProduct(Product product) {
		this.decoratedProduct = product;
	}

	@Override
	public BigDecimal getPrice() {
		return decoratedProduct.getPrice().multiply(
				BigDecimal.ONE.subtract(discount)
		);
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
