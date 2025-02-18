/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

import jakarta.persistence.Entity;

@Entity
public class Book extends MyProduct {
	private String author;


	public void setAuthor(String author) {
		this.author = author;
	}
}
