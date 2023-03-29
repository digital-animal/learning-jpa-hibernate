package com.zahid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.zahid.accesscard.AccessCard;
import com.zahid.emailgroup.EmailGroup;
import com.zahid.employee.Employee;

@SuppressWarnings("all")
public class ApplicationRead {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // readEntity();
        // readEntity2();
        readEntity3();
    }

    private static void readEntity() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee1 = entityManager.find(Employee.class, 1);
        // Employee employee2 = entityManager.find(Employee.class, 2);

        System.out.println(employee1);
        // System.out.println(employee2);

        System.out.println("# Fetched Employee");
        System.out.println(employee1.getName());
        System.out.println("# Fetched Access Card");
        System.out.println(employee1.getAccessCard());

        System.out.println("*********************************************************");

        AccessCard accessCard = entityManager.find(AccessCard.class, 2);
        // System.out.println(accessCard.getFirmwareVersion());
        System.out.println(accessCard.getEmployee());

        entityManager.close();
        entityManagerFactory.close();

    }

    private static void readEntity2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee1 = entityManager.find(Employee.class, 1);
        // Employee employee2 = entityManager.find(Employee.class, 2);

        System.out.println("# Fetched Employee");
        System.out.println(employee1);

        System.out.println("# Fetched Pay Check");
        System.out.println(employee1.getPayCheckList());

        entityManager.close();
        entityManagerFactory.close();

    }

    private static void readEntity3() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee1 = entityManager.find(Employee.class, 1);
        // Employee employee2 = entityManager.find(Employee.class, 2);
        
        EmailGroup emailGroup1 = entityManager.find(EmailGroup.class, 3);
        // EmailGroup emailGroup2 = entityManager.find(EmailGroup.class, 8);

        System.out.println("# Fetched Employee");
        System.out.println(employee1);
        System.out.println(employee1.getGroups());

        System.out.println("*****************************");

        System.out.println("# Fetched Email Group");
        System.out.println(emailGroup1);
        System.out.println(emailGroup1.getEmployees());

        entityManager.close();
        entityManagerFactory.close();

    }
}
