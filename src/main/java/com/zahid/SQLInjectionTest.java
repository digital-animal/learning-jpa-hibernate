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
public class SQLInjectionTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        readEntity();
    }

    public static void readEntity() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String minAge = "25";
        /* String minAge = "25 AND DELETE FROM Employee"; // SQL injection work in similar way
        TypedQuery<Employee> createQuery = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.age > " + minAge,
                Employee.class
        ); */

        TypedQuery<Employee> createQuery = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.age > :minAge",
                Employee.class
        );
        createQuery.setParameter("minAge", Integer.parseInt(minAge)); // avoidin SQL injection

        List<Employee> resultList = createQuery.getResultList();
        System.out.println(resultList.size());
        resultList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
