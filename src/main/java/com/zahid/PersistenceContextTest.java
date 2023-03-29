package com.zahid;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.github.javafaker.Faker;
import com.zahid.employee.Employee;
import com.zahid.employee.EmployeeType;

@SuppressWarnings("deprecation")
public class PersistenceContextTest {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        createEntity();
    }

    public static void createEntity() {
        Faker faker = new Faker();
        Random random = new Random();

        Employee employee = new Employee();
        employee.setName(faker.name().fullName());
        employee.setAge(random.nextInt(31) + 18);
        employee.setDob(new Date(random.nextInt(20) + 80, 0, 1)); // starts from 1900
        employee.setType(EmployeeType.values()[random.nextInt(EmployeeType.values().length)]);
        employee.setSsn(faker.numerify("###-###-####"));

        System.out.println("************************** Created employee instance");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        System.out.println("************************** Transaction begins");

        entityManager.persist(employee);
        // entityManager.flush(); // forcing entity manager to write to database i.e. commit 
        // entityManager.detach(employee); // not managed anymore
        // entityManager.clear();
        // entityManager.refresh(employee);

        System.out.println("************************** After persist method called");
        
        // entityManager.merge(employee); // to bring back detached entity to managed state

        Employee fetchedEmployee = entityManager.find(Employee.class, employee.getId());
        fetchedEmployee.setAge(32); // dirty
        // entityManager.refresh(fetchedEmployee);

        System.out.println("************************** After fetching employee (from cache)");
        System.out.println(fetchedEmployee);

        System.out.println(fetchedEmployee.hashCode()); // if detached, then java.lang.NullPointerException
        System.out.println(employee.hashCode());
        System.out.println(employee == fetchedEmployee);

        transaction.commit();
        System.out.println("************************** After commit i.e. transaction ends");

        entityManager.close();
        entityManagerFactory.close();
    }
}
