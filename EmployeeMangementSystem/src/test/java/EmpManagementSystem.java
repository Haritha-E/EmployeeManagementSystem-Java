import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
class Employee implements Serializable {
    int id;
    String name;
    long contact_no;
    String email_id;
    float payPerHour;
    int hoursWorked;

    public Employee(int id, String name, long contact_no, String email_id, float payPerHour, int hoursWorked) {
        this.id = id;
        this.name = name;
        this.contact_no = contact_no;
        this.email_id = email_id;
        this.payPerHour = payPerHour;
        this.hoursWorked = hoursWorked;
    }

    public String toString() {
        float salary = payPerHour * hoursWorked;
        return "\nEmployee Details :" + "\nID: " + this.id + "\nName: " + this.name + "\nContact No: " +
                this.contact_no + "\nEmail-id: " + this.email_id + "\nPay per Hour: " + this.payPerHour +
                "\nHours Worked: " + this.hoursWorked + "\nSalary: " + salary;
    }
}

public class EmpManagementSystem extends JFrame implements ActionListener {
    private ArrayList<Employee> employeeList;

    private JLabel labelId, labelName, labelContactNo, labelEmailId, labelPayPerHour, labelHoursWorked;
    private JTextField textFieldId, textFieldName, textFieldContactNo, textFieldEmailId, textFieldPayPerHour, textFieldHoursWorked;
    private JButton addButton, displayButton, deleteButton, calculateSalaryButton;

    public EmpManagementSystem() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        employeeList = new ArrayList<>();

        labelId = new JLabel("ID:");
        labelName = new JLabel("Name:");
        labelContactNo = new JLabel("Contact No:");
        labelEmailId = new JLabel("Email-ID:");
        labelPayPerHour = new JLabel("Hourly Rate:");
        labelHoursWorked = new JLabel("Hours Worked:");

        textFieldId = new JTextField(10);
        textFieldName = new JTextField(20);
        textFieldContactNo = new JTextField(15);
        textFieldEmailId = new JTextField(20);
        textFieldPayPerHour = new JTextField(10);
        textFieldHoursWorked = new JTextField(10);

        addButton = new JButton("Add Employee");
        addButton.addActionListener(this);
        displayButton = new JButton("Display Employees");
        displayButton.addActionListener(this);
        deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(this);
        calculateSalaryButton = new JButton("Calculate Salary");
        calculateSalaryButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(labelId);
        panel.add(textFieldId);
        panel.add(labelName);
        panel.add(textFieldName);
        panel.add(labelContactNo);
        panel.add(textFieldContactNo);
        panel.add(labelEmailId);
        panel.add(textFieldEmailId);
        panel.add(labelPayPerHour);
        panel.add(textFieldPayPerHour);
        panel.add(labelHoursWorked);
        panel.add(textFieldHoursWorked);
        panel.add(addButton);
        panel.add(displayButton);
        panel.add(deleteButton);
        panel.add(calculateSalaryButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            int id = Integer.parseInt(textFieldId.getText());
            String name = textFieldName.getText();
            long contactNo = Long.parseLong(textFieldContactNo.getText());
            String emailId = textFieldEmailId.getText();
            float payPerHour = Float.parseFloat(textFieldPayPerHour.getText());
            int hoursWorked = Integer.parseInt(textFieldHoursWorked.getText());
            employeeList.add(new Employee(id, name, contactNo, emailId, payPerHour, hoursWorked));
            JOptionPane.showMessageDialog(this, "Employee Added Successfully!");
        } else if (e.getSource() == displayButton) {
            displayEmployees();
        } else if (e.getSource() == deleteButton) {
            deleteEmployee();
        } else if (e.getSource() == calculateSalaryButton) {
            calculateAndDisplaySalary();
        }
    }

    private void displayEmployees() {
        StringBuilder display = new StringBuilder();
        display.append("--------------Employee List---------------\n");
        display.append(String.format("%-10s%-15s%-20s%-20s%-15s%-15s%-15s\n", "ID", "Name", "Contact No", "Email-Id", "Hourly Rate", "Hours Worked", "Salary"));
        for (Employee employee : employeeList) {
            float salary = employee.payPerHour * employee.hoursWorked;
            display.append(String.format("%-5s%-20s%-20s%-20s%-15s%-15s%-15s\n", employee.id, employee.name,
                    employee.contact_no, employee.email_id, employee.payPerHour, employee.hoursWorked, salary));
        }
        JOptionPane.showMessageDialog(this, display.toString());
    }

    private void deleteEmployee() {
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID of the employee to delete:"));
        boolean found = false;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).id == idToDelete) {
                employeeList.remove(i);
                found = true;
                JOptionPane.showMessageDialog(this, "Employee Deleted Successfully!");
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Employee with ID " + idToDelete + " not found!");
        }
    }

    private void calculateAndDisplaySalary() {
        int idToCalculate = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID of the employee to calculate salary:"));
        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.id == idToCalculate) {
                float salary = employee.payPerHour * employee.hoursWorked;
                JOptionPane.showMessageDialog(this, "Salary for employee " + employee.name + " (ID: " + employee.id + "): \u20B9" + salary);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Employee with ID " + idToCalculate + " not found!");
        }
    }

    public static void main(String[] args) {
        new EmpManagementSystem();
    }
}