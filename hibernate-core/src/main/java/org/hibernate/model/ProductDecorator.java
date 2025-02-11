/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.model;

public interface ProductDecorator {
	Product getDecoratedProduct();
	void setDecoratedProduct(Product product);
}
