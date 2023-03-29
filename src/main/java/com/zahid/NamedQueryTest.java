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
public class NamedQueryTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        readEntity();
    }

    public static void readEntity() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Query createNamedQuery = entityManager.createNamedQuery("employee_age_desc"); // generic type
        // TypedQuery<Employee> createNamedQuery = entityManager.createNamedQuery("employee_age_desc", Employee.class); // typed query
        TypedQuery<Employee> createNamedQuery = entityManager.createNamedQuery("employee_age_greater", Employee.class); // typed query with parameter
        createNamedQuery.setParameter("age", 30);
        List<Employee> resultList = createNamedQuery.getResultList();
        System.out.println(resultList.size());
        resultList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
