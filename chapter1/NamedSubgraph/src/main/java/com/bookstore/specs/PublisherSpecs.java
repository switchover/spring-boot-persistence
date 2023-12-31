package com.bookstore.specs;

import com.bookstore.entity.Publisher;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PublisherSpecs {
    private static final int ID = 2;

    public static Specification<Publisher> isIdGt2() {
        return (Root<Publisher> root,
                CriteriaQuery<?> query, CriteriaBuilder builder)
            -> builder.greaterThan(root.get("id"), ID);
    }
}
