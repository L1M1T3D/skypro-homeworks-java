package hw.skypro.skypro_homeworks.service;

import hw.skypro.skypro_homeworks.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();

    String initEmployees();

    List<String> printEmployees();

    List<String> printFullNames();

    String addEmployees(List<Employee> employees);

    String deleteEmployee(int id);

    String getEmployeeById(int id);
}
