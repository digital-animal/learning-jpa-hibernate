# JPA and Hibernate

## References

    - Pro JPA 2 in Java EE 8
    - Java Persistence with Hibernate

### JPA vs Hibernate

    - JPA is like interface
    - Hibernate is like implementation 
    - Hibernate is a JPA implementation


### JPA vs JDBC

    - JPA is database independent
    - JDBC is database dependent


### Caching in JPA

### SQL vs JPA speed comparison

### Rule of Thumb for JPA Relationship

    - @OneToOne relation => FetchType.EAGER
    - @OneTomay relation => FetchType.LAZY


### A refer to B, B refer to A. how to break cyclic relations. how does spring boot do that?

### Persistence Context(First Level Cache)

### Entity Life Cycle

    - Transient (Object Creation in Heap)
    - Managed (Persistence Context in Cache)
    - Removed
    - Detached

### JPQL

### SQL Injection