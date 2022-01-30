package com.github.tisv2000.video_library.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    void save(E entity);

    boolean update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

    boolean delete(K id);
}
