package hw.skypro.skypro_homeworks.service;

import hw.skypro.skypro_homeworks.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> getEmployeesByDepartment(int departmentId);

    double getSalarySumByDepartment(int departmentId);

    Employee getEmployeeWithMaxSalary(int departmentId);

    Employee getEmployeeWithMinSalary(int departmentId);

    Map<Integer, List<Employee>> getEmployeesByDepartment();
}