package hw.skypro.skypro_homeworks.controller;

import hw.skypro.skypro_homeworks.model.Employee;
import hw.skypro.skypro_homeworks.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }

    @GetMapping("/department/{id}/salary/sum")
    public double getSalarySumByDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getSalarySumByDepartment(departmentId);
    }

    @GetMapping("/department/{id}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/department/{id}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/department/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return departmentService.getEmployeesByDepartment();
    }
}