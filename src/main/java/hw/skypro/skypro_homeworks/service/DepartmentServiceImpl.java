package hw.skypro.skypro_homeworks.service;

import hw.skypro.skypro_homeworks.exceptions.DepartmentWasNotFound;
import hw.skypro.skypro_homeworks.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public double getSalarySumByDepartment(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() ->
                        new DepartmentWasNotFound(
                                "Отдела с номером " + departmentId + " не найдено!"
                        ));
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() ->
                        new DepartmentWasNotFound(
                                "Отдела с номером " + departmentId + " не найдено!"
                        ));
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }
}