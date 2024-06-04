# CQRS + ES Example
This is an example of CQRS (Command Query Responsibility Segregation) using Event Sourcing (ES)
- The Command application relies on MongoDB for storing the events
- The Query application relies on PostgresSQL as the reading store
- Kafka has been used for Command-Query applications communication
- Java 21 is the JDK selected for this example
- The project tries to follow a DDD package structure. However, maybe some classes are not located where they should.
- Heavy use of **Mediator** pattern in different ways. Example: Handlers have been separated in different classes instead of being in the same class.

## Diagram

![alt text](https://github.com/DanielMerchan/cqrs-es-java-spring-plain/blob/main/CQRS-ES-Diagram.png)

# Steps
Follow carefully the steps named in the files `<step>.md` to understand how to build this project.

Start with `0.md` and `1.md` which explains the initial setup (cloning the `initial` branch, not the `main` branch)

**Note**: I fixed the steps a lot of times while producing the step+1. Specially generic classes were a pain as I overused generic sometimes.

# Credits
Not all the credits are on me (of course), some of the code is mine, but some has been inspired from some good courses / tutorials I found online in blogs / Udemy.

So, it is possible you find some similarities with other online examples.

CQRS is not easy to build and understand. I strongly recommend to you to find a good online course where someone explains step by step :).



