package com.merchan.cqrses.example.policy.query.infrastructure;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.core.infrastructure.QueryDispatcher;
import com.merchan.cqrses.example.core.query.BaseQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PolicyQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, QueryHandler<? extends BaseQuery>> handlers = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerQueryHandler(Class<T> queryType, QueryHandler<T> queryHandler) {
        handlers.computeIfAbsent(queryType, c -> queryHandler);
    }

    @Override
    public <U extends BaseEntity> List<U> dispatch(BaseQuery query) {
        QueryHandler<BaseQuery> queryHandler = (QueryHandler<BaseQuery>) handlers.get(query.getClass());
        if (queryHandler == null) {
            throw new IllegalStateException("No handler registered for " + query.getClass().getName());
        }
       return queryHandler.handle(query);
    }
}
