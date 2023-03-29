package com.zahid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.zahid.emailgroup.EmailGroup;
import com.zahid.employee.Employee;

public class ApplicationUpdate {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        updateEntity();
    }

    private static void updateEntity() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 5);
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 3);

        employee.joinEmailGroup(emailGroup);
        emailGroup.addEmployee(employee);

        EntityTransaction transaction = entityManager.getTransaction();
        // # TRANSACTION BEGINS
        transaction.begin();
        entityManager.persist(employee);
        transaction.commit();
        // # TRANSACTION ENDS

        entityManager.close();
        entityManagerFactory.close();

    }

    
}
