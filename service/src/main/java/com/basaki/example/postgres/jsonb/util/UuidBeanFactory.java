package com.basaki.example.postgres.jsonb.util;

import java.util.UUID;
import org.dozer.BeanFactory;

/**
 * {@code UuidBeanFactory} configures Dozer mapper to map UUID fields.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/18/17
 */
public class UuidBeanFactory implements BeanFactory {
    @Override
    public Object createBean(Object source, Class<?> sourceClass,
            String targetBeanId) {
        if (source == null) {
            return null;
        }

        UUID uuidSrc = (UUID) source;
        UUID uuidDest = new UUID(uuidSrc.getMostSignificantBits(),
                uuidSrc.getLeastSignificantBits());
        return uuidDest;
    }
}
