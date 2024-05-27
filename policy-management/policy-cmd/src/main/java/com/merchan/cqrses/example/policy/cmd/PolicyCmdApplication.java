package com.merchan.cqrses.example.policy.cmd;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.core.handler.CommandHandler;
import com.merchan.cqrses.example.core.infrastructure.CommandDispatcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@SpringBootApplication
public class PolicyCmdApplication implements CommandLineRunner {

	private final ApplicationContext applicationContext;
	private final CommandDispatcher commandDispatcher;

	public PolicyCmdApplication(ApplicationContext applicationContext, CommandDispatcher commandDispatcher) {
		this.applicationContext = applicationContext;
		this.commandDispatcher = commandDispatcher;
	}

	public static void main(String[] args) {
		SpringApplication.run(PolicyCmdApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		this.registerCommandHandlers();
	}

	private void registerCommandHandlers() {
		Map<String, CommandHandler> beans = applicationContext.getBeansOfType(CommandHandler.class);
		for (CommandHandler handler : beans.values()) {
			// Find the generic type parameter of the handler
			Class<?> commandType = getCommandType(handler);
			if (commandType != null) {
				commandDispatcher.registerHandler((Class<? extends BaseCommand>) commandType, handler);
			}
		}
	}

	private Class<?> getCommandType(CommandHandler handler) {
		ParameterizedType parameterizedType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
		return (Class<?>) parameterizedType.getActualTypeArguments()[0];
	}
}
