package hw.skypro.skypro_homeworks.service;

import hw.skypro.skypro_homeworks.exceptions.DepartmentWasNotFound;
import hw.skypro.skypro_homeworks.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalaryAndDepServiceImpl implements SalaryAndDepService {

    public final EmployeeService employeeService;

    public SalaryAndDepServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new DepartmentWasNotFound("Отдела с номером " + departmentId + " не найдено!"));
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new DepartmentWasNotFound("Отдела с номером " + departmentId + " не найдено!"));
    }

    @Override
    public List<Employee> getEmployeesByDep(int departmentId) {
        return employeeService.getEmployeeList().stream()
                .filter(employee -> employee.getDepartament() == departmentId).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepAll() {
        return employeeService.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }
}
