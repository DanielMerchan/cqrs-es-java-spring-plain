package com.merchan.cqrses.example.policy.query;

import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.core.infrastructure.QueryDispatcher;
import com.merchan.cqrses.example.core.query.BaseQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@SpringBootApplication
public class PolicyQueryApplication implements CommandLineRunner {

	private final ApplicationContext applicationContext;
	private final QueryDispatcher queryDispatcher;

	public PolicyQueryApplication(ApplicationContext applicationContext, QueryDispatcher queryDispatcher) {
		this.applicationContext = applicationContext;
		this.queryDispatcher = queryDispatcher;
	}

	public static void main(String[] args) {
		SpringApplication.run(PolicyQueryApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		this.registerQueryHandlers();
	}

	private void registerQueryHandlers() {
		Map<String, QueryHandler> beans = applicationContext.getBeansOfType(QueryHandler.class);
		for (QueryHandler queryHandler : beans.values()) {
			Class<?> queryType = getQueryType(queryHandler);
			if (queryType != null) {
				queryDispatcher.registerQueryHandler((Class<? extends BaseQuery>)queryType, queryHandler);
			}
		}
	}

	private Class<?> getQueryType(QueryHandler handler) {
		ParameterizedType parameterizedType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
		return (Class<?>) parameterizedType.getActualTypeArguments()[0];
	}
}
