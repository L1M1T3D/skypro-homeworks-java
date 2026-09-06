package hw.skypro.skypro_homeworks.controller;
import java.util.ArrayList;
import java.util.List;

import hw.skypro.skypro_homeworks.model.Employee;
import hw.skypro.skypro_homeworks.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/add")
    public String addEmployees(@RequestParam("fullNames") List<String> fullName,
                              @RequestParam("departaments") List<Integer> departament,
                              @RequestParam("salaries") List<Double> salary) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < fullName.size(); i++) {
            Employee employee = new Employee(
                    fullName.get(i),
                    departament.get(i),
                    salary.get(i)
            );
            employees.add(employee);
        }
        return employeeService.addEmployees(employees);
    }

    @GetMapping("/employee/delete")
    public String deleteEmployee(@RequestParam("id") int id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/testinit")
    public String initEmployees() {
        return employeeService.initEmployees();
    }

    @GetMapping("/print-employees")
    public List<String> printEmployees() {
        return employeeService.printEmployees();
    }

    @GetMapping("employee/print-full-names")
    public List<String> printFullNames() {
        return employeeService.printFullNames();
    }

    @GetMapping("employee/get-by-id")
    public String getEmployeeById(@RequestParam("id") int id) {
        return employeeService.getEmployeeById(id);
    }
}
