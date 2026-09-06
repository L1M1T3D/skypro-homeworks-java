package hw.skypro.skypro_homeworks.service;

import hw.skypro.skypro_homeworks.model.Employee;

import java.util.List;
import java.util.Map;

public interface SalaryAndDepService {
    Employee getEmployeeWithMaxSalary(int departmentId);

    Employee getEmployeeWithMinSalary(int departmentId);

    List<Employee> getEmployeesByDep(int departmentId);

    Map<Integer, List<Employee>> getEmployeesByDepAll();
}
