package hw.skypro.skypro_homeworks.controller;

import hw.skypro.skypro_homeworks.model.Employee;
import hw.skypro.skypro_homeworks.service.SalaryAndDepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SalaryAndDepController {
    public final SalaryAndDepService salaryAndDepService;

    public SalaryAndDepController(SalaryAndDepService salaryAndDepService) {
        this.salaryAndDepService = salaryAndDepService;
    }

    @GetMapping("/departments/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("departmentId") int departmentId) {
        return salaryAndDepService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/departments/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("departmentId") int departmentId) {
        return salaryAndDepService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(value = "/departments/all", params = "departmentId")
    public List<Employee> getEmployeesByDep(@RequestParam("departmentId") int departmentId) {
        return salaryAndDepService.getEmployeesByDep(departmentId);
    }

    @GetMapping(value = "/departments/all", params = "!departmentId")
    public Map<Integer, List<Employee>> getEmployeesByDepAll() {
        return salaryAndDepService.getEmployeesByDepAll();
    }
}
