package com.basaki.example.postgres.jsonb.data.usertype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

/**
 * {@code JsonbUserType} converts a Postgres JSONB data type to a Java object
 * and vice versa
 * <p/>
 *
 * @since 3/13/17
 */
public class JsonbUserType implements UserType, ParameterizedType {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Class<?> clazz;

    @Override
    public void setParameterValues(Properties params) {
        String className = params.getProperty("className");

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new HibernateException("className not found", cnfe);
        }
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

    @Override
    public Class returnedClass() {
        return clazz;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        //equality operator (==) will cause extra update during delete
        //Please see TypeHelper.findDirty()
        //return x == y;
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names,
            SessionImplementor session,
            Object owner) throws HibernateException, SQLException {
        String json = rs.getString(names[0]);

        if (json == null) {
            return null;
        }

        try {
            return MAPPER.readValue(json.getBytes("UTF-8"), returnedClass());
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to convert String to " + returnedClass() + e.getMessage(),
                    e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
            SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            try {
                final StringWriter writer = new StringWriter();
                MAPPER.writeValue(writer, value);
                writer.flush();
                st.setObject(index, writer.toString(), Types.OTHER);
            } catch (IOException e) {
                throw new RuntimeException(
                        "Failed to convert " + returnedClass() + " to String " + e.getMessage(),
                        e);
            }
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value != null) {
            try {
                return MAPPER.readValue(MAPPER.writeValueAsString(value),
                        returnedClass());
            } catch (IOException e) {
                throw new HibernateException("Failed to deep copy object", e);
            }
        }
        return null;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new HibernateException("Failed to disassemble object", e);
        }
    }

    @Override
    public Object assemble(Serializable cached,
            Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target,
            Object owner) throws HibernateException {
        return deepCopy(original);
    }
}
