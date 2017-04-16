package com.basaki.example.postgres.jsonb.data.strategy;

/**
 * {@code DirtyStateIdentifiable} should be implemented by all entities where
 * {@code EntityDirtinessStrategy} strategy is to be used to identify the dirty
 * state of an enity during flush cycle. This strategy is used to prevent an
 * extra update database operation during creation of a new entity. During an
 * update operation, the dirty flag should be explicitly set to false.
 * <p/>
 *
 * @author Indra Basak
 * @since 4/13/17
 */
public interface DirtyStateIdentifiable {

    boolean isDirty();

    void setDirty(boolean dirty);
}
