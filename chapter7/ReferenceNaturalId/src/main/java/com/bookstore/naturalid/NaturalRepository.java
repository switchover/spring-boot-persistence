package com.bookstore.naturalid;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@NoRepositoryBean
public interface NaturalRepository<T, NID extends Serializable> {
    // use this method when your entity has a single field annotated with @NaturalId
    Optional<T> findBySimpleNaturalId(NID naturalId);

    // use this method when your entity has more than one field annotated with @NaturalId
    Optional<T> findByNaturalId(Map<String, Object> naturalIds);
}