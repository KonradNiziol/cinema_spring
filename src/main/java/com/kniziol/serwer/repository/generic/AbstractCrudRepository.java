package com.kniziol.serwer.repository.generic;



import com.kniziol.serwer.repository.connections.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractCrudRepository<T, ID> implements CrudRepository<T, ID> {

    @Autowired
    protected DbConnection dbConnection;
    private Class<T> entityType = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private Class<ID> idType = (Class<ID>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    @Override
    public Optional<T> add(T t) {
        final String SQL =
                "insert into " + entityType.getSimpleName() + " "
                + getColumnsForAdd() + " values " + getColumnsValuesForAdd(t) + ";";
        if (dbConnection.getJdbi().withHandle(handle -> handle.execute(SQL)) > 0) {
            return findLast();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T t) {
        final String SQL = "update " + entityType.getSimpleName() + " set " + getColumnsValuesForUpdate(t) + " where id = " + getIdValueForUpdate(t) + ";";
        if (dbConnection.getJdbi().withHandle(handle -> handle.execute(SQL)) > 0) {
            return findOne(getIdValueForUpdate(t));
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> findOne(ID id) {
        final String SQL = "select * from " + entityType.getSimpleName() + " where id = :id";
        return dbConnection.getJdbi().withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", id)
                .mapToBean(entityType)
                .findFirst());
    }

    @Override
    public List<T> findAll() {
        final String SQL = "select * from " + entityType.getSimpleName();
        return dbConnection.getJdbi().withHandle(handle -> handle
                .createQuery(SQL)
                .mapToBean(entityType)
                .list());
    }

    @Override
    public Optional<T> delete(ID id) {
        final String SQL = "DELETE FROM " + entityType.getSimpleName() + " where id = :id";
        return dbConnection.getJdbi().withHandle(handle -> handle
                .createQuery(SQL)
                .bind("id", id))
                .mapToBean(entityType)
                .findFirst();
    }

    @Override
    public void deleteAll() {
        final String SQL = "DELETE FROM " + entityType.getSimpleName();
        dbConnection.getJdbi().withHandle(handle -> handle.execute(SQL));

    }

    public Optional<T> findLast() {
        final String SQL = "select * from " + entityType.getSimpleName() + " order by id desc limit 1";
        return dbConnection.getJdbi().withHandle(handle -> handle.createQuery(SQL).mapToBean(entityType).findFirst());
    }

    // -----------------------------------------------------------------------
    // METODY POMOCNICZE
    // -----------------------------------------------------------------------
    private String getColumnsForAdd() {
        return "( " + Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().equals("id"))
                .map(Field::getName)
                .collect(Collectors.joining(", ")) + " ) ";
    }

    private String getColumnsValuesForAdd(T t) {
        return "( " + Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().equals("id"))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        if (field.getType().equals(String.class)) {
                            return "'" + field.get(t) + "'";
                        } else if (field.getType().equals(Date.class)){
                            return "str_to_date('" + field.get(t).toString() + "','%Y-%m-%d')";
                        }
                        return field.get(t).toString();
                    } catch (Exception e) {
                        throw new IllegalStateException("get columns values for add ex");
                    }
                })
                .collect(Collectors.joining(", ")) + " ) ";
    }

    private String getColumnsValuesForUpdate(T t) {
        return Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return !field.getName().equals("id") && field.get(t) != null;
                    } catch (Exception e) {
                        throw new IllegalStateException("get columns values for update ex 1");
                    }
                })
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        if (field.getType().equals(String.class)) {
                            return field.getName() + "='" + field.get(t) + "'";
                        }
                        return field.getName() + "=" + field.get(t).toString();
                    } catch (Exception e) {
                        throw new IllegalStateException("get columns values for update ex 2");
                    }
                })
                .collect(Collectors.joining(", "));
    }

    private ID getIdValueForUpdate(T t) {
        try {
            Field field = entityType.getDeclaredField("id");
            field.setAccessible(true);
            return (ID)field.get(t);
        } catch (Exception e) {
            throw new IllegalStateException("get id value for update ex");
        }
    }
}
