package com.fsad;

import java.sql.*;

public class JDBCCrud {

    public static void main(String[] args) {

        String url =
            "jdbc:mysql://localhost:3306/fsad?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String usr = "root";
        String pwd = "password";

        try (
            Connection con = DriverManager.getConnection(url, usr, pwd);
            Statement st = con.createStatement();
        ) {

            System.out.println("Database Connection Established");

            st.execute(
                "CREATE TABLE IF NOT EXISTS Dept (" +
                "dept_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "dept_name VARCHAR(20) UNIQUE)"
            );

            st.execute(
                "CREATE TABLE IF NOT EXISTS Emp (" +
                "emp_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "emp_name VARCHAR(50), " +
                "sal DOUBLE, " +
                "dept_id INT, " +
                "FOREIGN KEY (dept_id) REFERENCES Dept(dept_id))"
            );

            st.executeUpdate(
                "INSERT IGNORE INTO Dept (dept_name) VALUES ('CSE')"
            );

            ResultSet rs = st.executeQuery(
                "SELECT dept_id FROM Dept WHERE dept_name='CSE'"
            );
            rs.next();
            int deptId = rs.getInt(1);

            st.executeUpdate(
                "INSERT INTO Emp (emp_name, sal, dept_id) VALUES ('Saikiran', 25000, " + deptId + ")"
            );

            System.out.println("All operations successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
