package com.klu;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("====== Employee CRUD MENU ======");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(sc);
                    break;
                case 2:
                    viewEmployee(sc);
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    static void insertEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee emp = new Employee();

        System.out.print("Enter ID: ");
        emp.setId(sc.nextInt());

        System.out.print("Enter Name: ");
        emp.setName(sc.next());

        System.out.print("Enter Salary: ");
        emp.setSalary(sc.nextDouble());

        session.save(emp);
        tx.commit();
        session.close();

        System.out.println("Employee Added Successfully");
    }

    static void viewEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.println("ID: " + emp.getId());
            System.out.println("Name: " + emp.getName());
            System.out.println("Salary: " + emp.getSalary());
        } else {
            System.out.println("Employee Not Found");
        }

        session.close();
    }
    static void updateEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID to Update: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.print("Enter New Name: ");
            emp.setName(sc.next());

            System.out.print("Enter New Salary: ");
            emp.setSalary(sc.nextDouble());

            session.update(emp);
            tx.commit();
            System.out.println("Employee Updated Successfully");
        } else {
            System.out.println("Employee Not Found");
        }

        session.close();
    }

    static void deleteEmployee(Scanner sc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID to Delete: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.delete(emp);
            tx.commit();
            System.out.println("Employee Deleted Successfully");
        } else {
            System.out.println("Employee Not Found");
        }

        session.close();
    }
}
