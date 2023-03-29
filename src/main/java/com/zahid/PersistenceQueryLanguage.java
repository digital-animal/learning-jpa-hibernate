package com.zahid;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.zahid.employee.Employee;

// @SuppressWarnings({"deprecation", "unchecked"})
@SuppressWarnings("all")
public class PersistenceQueryLanguage {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // readEntity();
        readEntity2();
    }

    public static void readEntity2() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("**************** Names");
        TypedQuery<String> createQuery = entityManager.createQuery(
                "SELECT e.name FROM Employee e",
                String.class
        );
        List<String> names = createQuery.getResultList();
        System.out.println(names.size());
        names.forEach(System.out::println);


        System.out.println("**************** Ages");
        TypedQuery<Integer> createQuery2 = entityManager.createQuery(
                "SELECT e.age FROM Employee e",
                Integer.class
        );
        List<Integer> ages = createQuery2.getResultList();
        System.out.println(ages.size());
        ages.forEach(System.out::println);


        System.out.println("**************** Names, Ages Object List");
        Query createQuery3 = entityManager.createQuery(
                "SELECT e.name, e.age FROM Employee e"
        );
        List objectList = createQuery3.getResultList();
        System.out.println(objectList.size());
        objectList.forEach(System.out::println);


        System.out.println("**************** Names, Ages List");
        TypedQuery<Object[]> createQuery4 = entityManager.createQuery(
                // "SELECT e.name, e.age FROM Employee e",
                "SELECT e.name, e.age, e.type, e.ssn FROM Employee e",
                Object[].class
        );
        List<Object[]> nameAgeList = createQuery4.getResultList();
        System.out.println(nameAgeList.size());
        // nameAgeList.forEach(System.out::println);
        nameAgeList.forEach(e -> System.out.println("[" + e[0] + ", " + e[1] + ", " + e[2] + ", " + e[3] + "]"));

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void readEntity() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // TypedQuery<Employee> createQuery = entityManager.createQuery("SELECT e FROM
        // Employee e", Employee.class);
        // TypedQuery<Employee> createQuery = entityManager.createQuery("SELECT e FROM
        // Employee e WHERE LOWER(e.name) LIKE 'r%'", Employee.class);
        // TypedQuery<Employee> createQuery = entityManager.createQuery("SELECT e FROM
        // Employee e WHERE e.age > 29", Employee.class);
        // TypedQuery<Employee> createQuery = entityManager.createQuery("SELECT e FROM
        // Employee e WHERE e.age > 29 ORDER BY e.age DESC", Employee.class);
        // TypedQuery<Employee> createQuery = entityManager.createQuery("SELECT e FROM
        // Employee e WHERE e.age > 24 AND e.age < 30 ORDER BY e.age DESC",
        // Employee.class);
        // TypedQuery<Employee> createQuery = entityManager.createQuery("SELECT e FROM
        // Employee e WHERE e.age BETWEEN 24 AND 29 ORDER BY e.age DESC",
        // Employee.class);
        TypedQuery<Employee> createQuery = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.accessCard.isActive = true", // implicit join already exists
                Employee.class);
        List<Employee> resultList = createQuery.getResultList();

        System.out.println(resultList.size());
        // resultList.forEach(System.out::println);
        resultList.forEach(e -> System.out.println(e.getName()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
