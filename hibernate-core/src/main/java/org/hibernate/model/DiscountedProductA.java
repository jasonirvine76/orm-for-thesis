/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "discounted_products_a")
public class DiscountedProductA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "decorated_entity_id")
	private Long productId;

	private BigDecimal discountPercentage;
}
