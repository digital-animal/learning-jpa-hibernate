package com.zahid;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.github.javafaker.Faker;
import com.zahid.accesscard.AccessCard;
import com.zahid.emailgroup.EmailGroup;
import com.zahid.employee.Employee;
import com.zahid.employee.EmployeeType;
import com.zahid.paycheck.PayCheck;

@SuppressWarnings("all")
public class ApplicationWrite {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // createEntity();
        // readEntity();
        // updateEntity();
        // deleteEntity();
        
        createEntity2();
        createEntity3();
    }

    private static void deleteEntity() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee1 = entityManager.find(Employee.class, 1);
        System.out.println(employee1);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.remove(employee1);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    private static void updateEntity() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee1 = entityManager.find(Employee.class, 1);
        System.out.println(employee1);

        employee1.setAge(19);
        employee1.setType(EmployeeType.FULL_TIME);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(employee1);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println(employee1);
    }

    public static void createEntity() {
        Faker faker = new Faker();
        Random random = new Random();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        for (int i = 0; i < 10; i++) {
            AccessCard accessCard = new AccessCard();
            accessCard.setIssueDate(new Date());
            accessCard.setIsActive(random.nextBoolean());
            accessCard.setFirmwareVersion("1.0");

            Employee employee = new Employee();
            // employee.setId(i+1);
            employee.setName(faker.name().fullName());
            employee.setAge(random.nextInt(31) + 18);
            employee.setDob(new Date(random.nextInt(20) + 80, 0, 1)); // starts from 1900
            employee.setType(EmployeeType.values()[random.nextInt(EmployeeType.values().length)]);
            employee.setSsn(faker.numerify("###-###-####"));
            // employee.setSsn(faker.numerify("##########"));

            employee.setAccessCard(accessCard);
            accessCard.setEmployee(employee);

            transaction.begin();
            // entityManager.persist(accessCard);
            entityManager.persist(employee); // employee is thr owner of the relationship, so saving employee saves
                                             // access_card too
            transaction.commit();
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void createEntity2() {
        Faker faker = new Faker();
        Random random = new Random();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        // # ACCESSCARD INFORMATION
        AccessCard accessCard1 = new AccessCard();
        accessCard1.setIssueDate(new Date());
        accessCard1.setIsActive(random.nextBoolean());
        accessCard1.setFirmwareVersion("1.0.0");
        
        AccessCard accessCard2 = new AccessCard();
        accessCard2.setIssueDate(new Date());
        accessCard2.setIsActive(random.nextBoolean());
        accessCard2.setFirmwareVersion("1.0.1");
        
        // # PAYCHECK INFORMATION
        PayCheck payCheck1 = new PayCheck();
        payCheck1.setPayPeriodStart(new Date());
        payCheck1.setPayPeriodEnd(new Date());
        payCheck1.setSalary(random.nextInt(2000) + 1000);

        PayCheck payCheck2 = new PayCheck();
        payCheck2.setPayPeriodStart(new Date());
        payCheck2.setPayPeriodEnd(new Date());
        payCheck2.setSalary(random.nextInt(2000) + 1000);

        // # EMPLOYEE INFORMATION
        Employee employee1 = new Employee();
        employee1.setName(faker.name().fullName());
        employee1.setAge(random.nextInt(31) + 18);
        employee1.setDob(new Date(random.nextInt(20) + 80, 0, 1)); // starts from 1900
        employee1.setType(EmployeeType.values()[random.nextInt(EmployeeType.values().length)]);
        employee1.setSsn(faker.numerify("###-###-####"));
        
        Employee employee2 = new Employee();
        employee2.setName(faker.name().fullName());
        employee2.setAge(random.nextInt(31) + 18);
        employee2.setDob(new Date(random.nextInt(20) + 80, 0, 1)); // starts from 1900
        employee2.setType(EmployeeType.values()[random.nextInt(EmployeeType.values().length)]);
        employee2.setSsn(faker.numerify("###-###-####"));
        
        employee1.setAccessCard(accessCard1);
        employee2.setAccessCard(accessCard2);
        
        payCheck1.setEmployee(employee1);
        payCheck2.setEmployee(employee1);
        
        accessCard1.setEmployee(employee1);
        accessCard2.setEmployee(employee2);

        transaction.begin();
        // entityManager.persist(accessCard1);
        // entityManager.persist(accessCard2);

        // employee1.setPayCheckList(Arrays.asList(payCheck1, payCheck2));
        employee1.addPayCheck(payCheck1);
        employee1.addPayCheck(payCheck2);
        
        entityManager.persist(employee1); // employee is thr owner of the relationship, so saving employee saves access_card too
        entityManager.persist(employee2); // employee is thr owner of the relationship, so saving employee saves access_card too

        entityManager.persist(payCheck1);
        entityManager.persist(payCheck2);


        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void createEntity3() {
        Faker faker = new Faker();
        Random random = new Random();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        // # ACCESSCARD INFORMATION
        AccessCard accessCard1 = new AccessCard();
        accessCard1.setIssueDate(new Date());
        accessCard1.setIsActive(random.nextBoolean());
        accessCard1.setFirmwareVersion("1.0.0");
        
        AccessCard accessCard2 = new AccessCard();
        accessCard2.setIssueDate(new Date());
        accessCard2.setIsActive(random.nextBoolean()); 
        accessCard2.setFirmwareVersion("1.0.1");
        
        // # PAYCHECK INFORMATION
        PayCheck payCheck1 = new PayCheck();
        payCheck1.setPayPeriodStart(new Date());
        payCheck1.setPayPeriodEnd(new Date());
        payCheck1.setSalary(random.nextInt(2000) + 1000);

        PayCheck payCheck2 = new PayCheck();
        payCheck2.setPayPeriodStart(new Date());
        payCheck2.setPayPeriodEnd(new Date());
        payCheck2.setSalary(random.nextInt(2000) + 1000);

        // # EMPLOYEE INFORMATION
        Employee employee1 = new Employee();
        employee1.setName(faker.name().fullName());
        employee1.setAge(random.nextInt(31) + 18);
        employee1.setDob(new Date(random.nextInt(20) + 80, 0, 1)); // starts from 1900
        employee1.setType(EmployeeType.values()[random.nextInt(EmployeeType.values().length)]);
        employee1.setSsn(faker.numerify("###-###-####"));
        
        Employee employee2 = new Employee();
        employee2.setName(faker.name().fullName());
        employee2.setAge(random.nextInt(31) + 18);
        employee2.setDob(new Date(random.nextInt(20) + 80, 0, 1)); // starts from 1900
        employee2.setType(EmployeeType.values()[random.nextInt(EmployeeType.values().length)]);
        employee2.setSsn(faker.numerify("###-###-####"));
        
        employee1.setAccessCard(accessCard1);
        employee2.setAccessCard(accessCard2);
        
        // # EMAIL GROUP INFORMATION
        EmailGroup emailGroup1 = new EmailGroup();
        emailGroup1.setName("ODMS Developer Group");

        EmailGroup emailGroup2 = new EmailGroup();
        emailGroup2.setName("OpenCRVS Developer Group");

        EmailGroup emailGroup3 = new EmailGroup();
        emailGroup3.setName("FreightForwarding Developer Group");
        
        payCheck1.setEmployee(employee1);
        payCheck2.setEmployee(employee1);
        
        accessCard1.setEmployee(employee1);
        accessCard2.setEmployee(employee2);

        employee1.joinEmailGroup(emailGroup1);
        emailGroup1.addEmployee(employee1);

        employee1.joinEmailGroup(emailGroup2);
        emailGroup2.addEmployee(employee1);

        employee2.joinEmailGroup(emailGroup2);
        emailGroup2.addEmployee(employee2);

        employee2.joinEmailGroup(emailGroup3);
        emailGroup3.addEmployee(employee2);

        transaction.begin();
        // entityManager.persist(accessCard1);
        // entityManager.persist(accessCard2);

        // employee1.setPayCheckList(Arrays.asList(payCheck1, payCheck2));
        employee1.addPayCheck(payCheck1);
        employee1.addPayCheck(payCheck2);
        
        entityManager.persist(employee1); // employee is thr owner of the relationship, so saving employee saves access_card too
        entityManager.persist(employee2); // employee is thr owner of the relationship, so saving employee saves access_card too

        entityManager.persist(payCheck1);
        entityManager.persist(payCheck2);

        entityManager.persist(emailGroup1);
        entityManager.persist(emailGroup2);
        entityManager.persist(emailGroup3);

        entityManager.persist(emailGroup1);
        entityManager.persist(emailGroup2);
        entityManager.persist(emailGroup3);

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
