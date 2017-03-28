package com.basaki.example.postgres.jsonb.data.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;
import org.postgresql.util.PGobject;

/**
 * {@code PGEnumUserType} converts a Java Enum to Postgres Enum type and vice
 * versa.
 * <p/>
 *
 * @since 3/8/17
 */
public class PGEnumUserType implements EnhancedUserType, ParameterizedType {
    private Class<Enum> enumClass;

    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClassName");
        try {
            enumClass = (Class<Enum>) Class.forName(enumClassName);
        } catch (ClassNotFoundException cnfe) {
            throw new HibernateException("Enum class not found", cnfe);
        }
    }

    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Enum) value;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names,
            SessionImplementor session,
            Object owner) throws HibernateException, SQLException {
        Object object = rs.getObject(names[0]);
        if (rs.wasNull()) {
            return null;
        }

        // Converts a PostGrem Enum from PGobject to Java Enum
        if (object instanceof PGobject) {
            PGobject pg = (PGobject) object;
            return Enum.valueOf(enumClass, pg.getValue());
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
            SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            // Types.OTHER (1111) gets mapped to Postgres Enum
            st.setObject(index, ((Enum) value), Types.OTHER);
        }
    }

    public boolean isMutable() {
        return false;
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    public Class returnedClass() {
        return enumClass;
    }

    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    public Object fromXMLString(String xmlValue) {
        return Enum.valueOf(enumClass, xmlValue);
    }

    public String objectToSQLString(Object value) {
        return '\'' + ((Enum) value).name() + '\'';
    }

    public String toXMLString(Object value) {
        return ((Enum) value).name();
    }
}