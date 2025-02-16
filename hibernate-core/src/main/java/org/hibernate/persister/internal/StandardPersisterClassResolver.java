/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.persister.internal;

import org.hibernate.mapping.Collection;
import org.hibernate.mapping.DeltaClass;
import org.hibernate.mapping.JoinedSubclass;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.RootClass;
import org.hibernate.mapping.SingleTableSubclass;
import org.hibernate.mapping.UnionSubclass;
import org.hibernate.persister.collection.BasicCollectionPersister;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.persister.collection.OneToManyPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.JoinedSubclassEntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.persister.entity.UnionSubclassEntityPersister;
import org.hibernate.persister.spi.PersisterClassResolver;
import org.hibernate.persister.spi.UnknownPersisterException;

/**
 * @author Steve Ebersole
 */
public class StandardPersisterClassResolver implements PersisterClassResolver {

	@Override
	public Class<? extends EntityPersister> getEntityPersisterClass(PersistentClass model) {
		// todo : make sure this is based on an attribute kept on the metamodel in the new code,
		//        not the concrete PersistentClass impl found!
		System.out.println("StandardPersisterClassResolver: " + model.getClassName() + " " + model.getClass());
		if ( model instanceof RootClass ) {
			if ( model.hasSubclasses() ) {
				//If the class has children, we need to find of which kind
				model = model.getDirectSubclasses().get(0);
			}
			else {
				return singleTableEntityPersister();
			}
		}
		System.out.println("StandardPersisterClassResolver hasSubclass: " + model.getClassName() + " " + model.getClass());
		if ( model instanceof JoinedSubclass ) {
			return joinedSubclassEntityPersister();
		}
		else if ( model instanceof UnionSubclass ) {
			return unionSubclassEntityPersister();
		}
		else if ( model instanceof SingleTableSubclass ) {
			return singleTableEntityPersister();
		}
		else if ( model instanceof DeltaClass ) {
			// TODO: Return DeltaClass persister
			return singleTableEntityPersister();
		}
		else {
			throw new UnknownPersisterException(
					"Could not determine persister implementation for entity [" + model.getEntityName() + "]"
			);
		}
	}

	public Class<? extends EntityPersister> singleTableEntityPersister() {
		return SingleTableEntityPersister.class;
	}

	public Class<? extends EntityPersister> joinedSubclassEntityPersister() {
		return JoinedSubclassEntityPersister.class;
	}

	public Class<? extends EntityPersister> unionSubclassEntityPersister() {
		return UnionSubclassEntityPersister.class;
	}

	@Override
	public Class<? extends CollectionPersister> getCollectionPersisterClass(Collection metadata) {
		return metadata.isOneToMany() ? oneToManyPersister() : basicCollectionPersister();
	}

	private Class<OneToManyPersister> oneToManyPersister() {
		return OneToManyPersister.class;
	}

	private Class<BasicCollectionPersister> basicCollectionPersister() {
		return BasicCollectionPersister.class;
	}
}
