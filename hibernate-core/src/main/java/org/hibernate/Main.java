/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.model.DiscountedProduct;

public class Main {
	public static void main(String[] args) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
				.applySetting("hibernate.hbm2ddl.auto", "create-drop")
//				.applySetting("hibernate.integrator_provider", (Integrator) new DeltaIntegrator()) // Use custom integrator
				.build();

		MetadataSources metadataSources = new MetadataSources(registry);
		metadataSources.addAnnotatedClass( DiscountedProduct.class);

		Metadata metadata = metadataSources.buildMetadata();

		System.out.println("==== ENTITY MAPPING RESULT ====");
		for ( PersistentClass pc : metadata.getEntityBindings()) {
			System.out.println("Class: " + pc.getClassName() + " -> Type: " + pc.getClass().getSimpleName());
		}

		StandardServiceRegistryBuilder.destroy(registry);
	}
}
