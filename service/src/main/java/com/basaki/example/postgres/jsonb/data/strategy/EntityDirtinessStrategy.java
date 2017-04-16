package com.basaki.example.postgres.jsonb.data.strategy;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CustomEntityDirtinessStrategy;
import org.hibernate.Session;
import org.hibernate.persister.entity.EntityPersister;

/**
 * {@code EntityDirtinessStrategy} is the strategy used for identifying if a
 * {@code DirtyStateIdentifiable} entity is in dirty state during flush cycle.
 * This strategy is used to prevent an extra update database operation during
 * creation of a new entity. During an update operation, the dirty flag should
 * be explicitly set to false.
 * <p/>
 *
 * @author Indra Basak
 * @since 4/13/17
 */
@Slf4j
public class EntityDirtinessStrategy implements CustomEntityDirtinessStrategy {

    @Override
    public boolean canDirtyCheck(Object entity, EntityPersister persister,
            Session session) {
        if (entity instanceof DirtyStateIdentifiable) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDirty(Object entity, EntityPersister persister,
            Session session) {
        DirtyStateIdentifiable identifiableEntity =
                (DirtyStateIdentifiable) entity;

        if (identifiableEntity.isDirty()) {
            return true;
        }

        return false;
    }

    @Override
    public void resetDirty(Object entity, EntityPersister persister,
            Session session) {
        if (entity instanceof DirtyStateIdentifiable) {
            ((DirtyStateIdentifiable) entity).setDirty(false);
        }
    }

    @Override
    public void findDirty(Object entity, EntityPersister persister,
            Session session,
            DirtyCheckContext dirtyCheckContext) {
        //not needed
        //        dirtyCheckContext.doDirtyChecking(
        //                new AttributeChecker() {
        //                    @Override
        //                    public boolean isDirty(AttributeInformation attr) {
        //                        log.debug(attr.getName());
        //                        return false;
        //                    }
        //                });

    }
}
