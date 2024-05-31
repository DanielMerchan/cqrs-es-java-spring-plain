package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.core.query.BaseQuery;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerQueryHandler(Class<T> queryType, QueryHandler<T> queryHandler);
    <U extends BaseEntity> List<U> dispatch(BaseQuery query);
}
