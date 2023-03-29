package com.zahid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.zahid.employee.Employee;

public class ApplicationDelete {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        deleteEntity();
    }

    private static void deleteEntity() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 1);

        EntityTransaction transaction = entityManager.getTransaction();
        // # TRANSACTION BEGINS
        transaction.begin();
        
        entityManager.remove(employee);

        transaction.commit();
        // # TRANSACTION ENDS

        entityManager.close();
        entityManagerFactory.close();

    }

    
}
