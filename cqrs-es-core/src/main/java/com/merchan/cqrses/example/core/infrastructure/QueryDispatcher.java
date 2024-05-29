package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.core.query.BaseQuery;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerQueryHandler(Class<T> queryType, QueryHandler<T> queryHandler);
    <T extends BaseQuery> List<BaseEntity> dispatch(T query);
}
