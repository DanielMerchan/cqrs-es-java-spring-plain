package com.merchan.cqrses.example.core.handler;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.query.BaseQuery;

import java.util.List;

@FunctionalInterface
public interface QueryHandler<T extends BaseQuery> {
    <U extends BaseEntity> List<U> handle(T query);
}
