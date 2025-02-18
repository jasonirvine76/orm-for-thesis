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
import org.hibernate.model.BasicProduct;
import org.hibernate.model.Book;
import org.hibernate.model.DiscountedProductA;
import org.hibernate.model.DiscountedProductB;
import org.hibernate.model.MyProduct;
import org.hibernate.model.Product;


public class Main {
	public static void main(String[] args) {
		// ✅ Configure PostgreSQL instead of H2
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
				.applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
				.applySetting("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hibernateorm")
				.applySetting("hibernate.connection.username", "postgres")
				.applySetting("hibernate.connection.password", "admin")
				.applySetting("hibernate.show_sql", "true")
				.applySetting("hibernate.format_sql", "true")
				.applySetting("hibernate.hbm2ddl.auto", "update")
				.build();

		MetadataSources metadataSources = new MetadataSources(registry);
		metadataSources.addAnnotatedClass( Product.class );
//		metadataSources.addAnnotatedClass( DiscountedProduct.class);
		metadataSources.addAnnotatedClass( DiscountedProductB.class );
		metadataSources.addAnnotatedClass( BasicProduct.class );
		metadataSources.addAnnotatedClass( DiscountedProductA.class );
		metadataSources.addAnnotatedClass( MyProduct.class );
		metadataSources.addAnnotatedClass( Book.class );

		Metadata metadata = metadataSources.buildMetadata();

		System.out.println("==== ENTITY MAPPING RESULT ====");
		for (PersistentClass pc : metadata.getEntityBindings()) {
			System.out.println("Class: " + pc.getClassName() + " -> Type: " + pc.getClass().getSimpleName());
		}

		try (SessionFactory sessionFactory = metadata.buildSessionFactory();
			Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			BasicProduct laptop = new BasicProduct();
			laptop.setName("Premium Laptop");
//			laptop.setPrice(new BigDecimal(10));
			session.persist(laptop);
			System.out.println("=========================================================================");
			Book book = new Book();
			book.setAuthor( "Jono" );
			book.setName( "Cara Menambang" );
			session.persist( book );
			System.out.println("=========================================================================");
			DiscountedProductB product = new DiscountedProductB();
			product.setName("Discounted Laptop");
			product.setProduct( laptop );
//			product.setDiscount(new BigDecimal( 10 ) ); // Assume discount is in %

			session.persist(product);
			System.out.println("=========================================================================");
			session.getTransaction().commit();
			System.out.println("=========================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}

		StandardServiceRegistryBuilder.destroy(registry);
	}
}
