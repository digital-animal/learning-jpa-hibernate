# JPA and Hibernate

## References

    1. Pro JPA 2 in Java EE 8
    2. Java Persistence with Hibernate

## JPA vs Hibernate

JPA is like interface while Hibernate is like implementatio. Hibernate is a JPA implementation.

JPA (Java Persistence API) is a specification that defines a standard way to access relational databases in Java applications. Hibernate is a popular open-source implementation of the JPA specification.

In other words, Hibernate is a concrete implementation of the JPA specification, which means that it implements the JPA API and provides additional features that are not part of the JPA standard.


## JPA vs JDBC

JPA is database independent while JDBC is database specific.

JPA (Java Persistence API) and JDBC (Java Database Connectivity) are both used to access relational databases in Java applications, but they differ in their approach and functionality.

JPA provides an object-relational mapping (ORM) framework that maps Java objects to relational database tables. JDBC, on the other hand, does not provide any ORM framework, and the developer is responsible for writing SQL queries to interact with the database.

JPA provides a higher level of abstraction than JDBC, which means that developers do not have to deal with low-level details such as database connections, transactions, and query optimization. JDBC, on the other hand, is a low-level API that requires developers to handle these details themselves.

JPA is designed to be portable across different databases, which means that the same code can be used with different databases without any modification. JDBC, on the other hand, requires database-specific code to be written for each database.


### Caching in JPA

Caching is an important performance optimization technique used in software applications, including those that use JPA (Java Persistence API) for database access. JPA supports two types of caching: first-level cache and second-level cache.

JPA has a first-level cache, also known as an entity manager cache, that is enabled by default. The first-level cache stores entities that have been read or persisted in the current transaction. When an entity is read or persisted, it is stored in the cache and subsequent requests for the same entity are served from the cache instead of the database. The first-level cache is scoped to the entity manager instance, which means that entities cached by one entity manager instance are not visible to other entity manager instances.

JPA also supports a second-level cache, which is a shared cache that can be used by multiple entity manager instances. The second-level cache stores entities in a cache that is shared across multiple entity manager instances, which allows entities to be retrieved from the cache even if they were not read or persisted in the current transaction. The second-level cache is configurable and can be enabled or disabled on a per-entity basis.


### SQL vs ORM speed comparison

SQL and ORM (Object-Relational Mapping) are two different approaches to interacting with relational databases. SQL is a language used to manage and manipulate data in a relational database, while ORM is a programming technique that maps objects to database tables and vice versa.

When it comes to speed comparison between SQL and ORM, there is no definitive answer, as it depends on various factors, such as the complexity of the database schema, the size of the database, the number of queries performed, the network latency, and the hardware resources available.

In general, raw SQL queries tend to be faster than ORM queries, as they allow more control over the database queries and can be optimized for specific use cases. Additionally, ORM frameworks may add some overhead to the queries, as they need to translate the object-oriented syntax into SQL statements.

However, ORM can provide many advantages, such as a simpler and more natural way to interact with the database, better code readability and maintainability, and increased security and protection against SQL injection attacks.

In practice, the performance difference between SQL and ORM is often negligible for small to medium-sized databases or simple queries. However, for more complex databases and queries, SQL may be the better choice for performance reasons, while ORM may be preferred for ease of use and productivity.


## Rule of Thumb for JPA Relationship

    @OneToOne relation FetchType.EAGER, CasCadeType.ALL
    @OneTomay relation FetchType.LAZY, CasCadeType.ALL

When designing a JPA relationship between entities, a common rule of thumb is to follow the "one-to-many" and "many-to-one" cardinality constraints.

In general, a "one-to-many" relationship exists when one entity instance is related to multiple instances of another entity, and a "many-to-one" relationship exists when multiple instances of one entity are related to a single instance of another entity.

To implement these relationships in JPA, the best practice is to use the appropriate annotation for each cardinality constraint. For example, the @OneToMany annotation is used to indicate a one-to-many relationship, and the @ManyToOne annotation is used to indicate a many-to-one relationship.

Another important aspect to consider when designing JPA relationships is the cascading behavior, which determines the propagation of operations (such as insert, update, and delete) across the entities involved in the relationship. The cascading behavior can also be configured using annotations, such as @CascadeType.ALL or @CascadeType.PERSIST.

It's important to keep in mind that while JPA provides a convenient and flexible way to manage relationships between entities, it's crucial to properly design and optimize the relationships based on the application requirements, database schema, and performance considerations.


## In Spring, if class A has a reference to class B and class B has a reference to class A, and we want to insert objects of both classes into a database simultaneously, how does Spring deal with the circular dependency issue?

Spring uses a mechanism called "cascading" to manage circular dependencies between objects when persisting them to a database.

In your case, when you insert an object of class A and an object of class B into the database simultaneously, Spring will recognize the circular dependency between them and will persist them in the correct order to avoid any issues. Specifically, when the object of class A is saved to the database, Spring will also save the object of class B that it refers to. Similarly, when the object of class B is saved to the database, Spring will save the object of class A that it refers to.

Spring's cascading mechanism is based on the notion of "transitive persistence", which means that when you persist an object, any related objects that are marked as "cascade" will also be persisted. In this case, Spring would mark the relationship between class A and class B as "cascade", so that when an object of class A is saved, the object of class B it refers to is also saved.


## Persistence Context(First Level Cache)

The Persistence Context is a set of managed entity instances that are currently being persisted in the database. When you load an entity from the database, Spring stores it in the Persistence Context. Subsequent requests for the same entity will be served from the Persistence Context instead of querying the database again. This reduces the number of queries to the database and can improve performance.

The Persistence Context is associated with a transaction, and any changes made to the managed entity instances in the Persistence Context are not immediately persisted to the database. Instead, the changes are tracked and will be flushed to the database when the transaction is committed. This ensures that the database remains consistent and that the changes made during a transaction are atomic.

Spring's implementation of the Persistence Context is based on the Java Persistence API (JPA), which provides a standard way of working with databases in Java applications. In a Spring application, you can configure the Persistence Context using annotations or XML configuration files, and you can choose from a variety of JPA providers, such as Hibernate or EclipseLink.


### Entity Life Cycle

    Transient (Object Creation in Heap)
    Managed (Persistence Context in Cache)
    Removed
    Detached

In Spring, an entity's life cycle refers to the different states that an object goes through as it interacts with the persistence layer of an application.

The life cycle of an entity in Spring typically consists of four states:

    New (Transient): The entity is not yet associated with the persistence context and has not been saved to the database.

    Managed: The entity is associated with the persistence context and any changes made to it will be tracked and persisted when the persistence context is flushed.

    Detached: The entity was previously managed but has been removed from the persistence context. Any changes made to the entity will not be tracked or persisted.

    Removed: The entity is marked for removal from the database. It will be deleted when the persistence context is flushed.

Spring provides a number of APIs and tools to help manage the life cycle of entities, such as the EntityManager and JPARepository. These tools allow you to persist, update, and remove entities from the database, and also provide mechanisms for querying and retrieving entities based on various criteria.


### JPQL

JPQL (Java Persistence Query Language) is a query language that is used to retrieve data from a database using Java Persistence API (JPA) in Spring.

JPQL is a SQL-like language that is used to query JPA entities. It supports the standard SQL syntax, as well as additional object-oriented concepts, such as inheritance and polymorphism. JPQL queries are executed against a JPA entity manager, which translates the queries into native SQL statements that are executed against the database.

In a Spring application, you can use JPQL to query JPA entities using the EntityManager or the JPARepository. 

JPQL can be used to perform a wide range of queries, including simple queries that retrieve single entities or collections of entities, and more complex queries that join multiple tables or use aggregation functions.


## SQL Injection

SQL injection is a type of security vulnerability that can occur when an application fails to properly validate and sanitize user input before using it in a SQL query. This can allow an attacker to execute malicious SQL code against the application's database, potentially compromising sensitive data or even taking control of the entire system.

SQL injection attacks typically involve inserting malicious SQL code into user input fields, such as login forms or search boxes, with the intention of manipulating the SQL statement executed by the application. For example, an attacker might insert a command that deletes all records in a database or returns sensitive information.

To prevent SQL injection attacks, it is important to properly validate and sanitize all user input before using it in a SQL query. This can be done by using prepared statements or parameterized queries, which separate the SQL code from the user input and ensure that the input is properly escaped and sanitized. It is also important to limit the privileges of the user account used by the application to access the database, to minimize the impact of any successful attack.


## Transaction Management

Transaction management is an essential part of developing robust and reliable enterprise applications. Spring Framework provides a comprehensive and flexible way to handle transactions in Java applications.

Spring's transaction management model is based on the Spring Framework's transaction abstraction, which provides a consistent programming model for different transaction APIs, including Java Transaction API (JTA), JDBC, Hibernate, and JPA.


### @Transactional Annotation

`@Transactional` is an annotation in Spring Framework that is used to demarcate transactional boundaries around methods. When you annotate a method with @Transactional, Spring creates a transaction around that method, which means that any database operations performed within that method will be executed within a transaction.

The `@Transactional` annotation can be applied to a method or a class. If applied to a class, it will apply to all public methods of that class that are not already annotated with `@Transactional`.
