/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products_a")
public class ProductA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private BigDecimal price;
}
