package hw.skypro.skypro_homeworks.controller;

import hw.skypro.skypro_homeworks.model.Employee;
import hw.skypro.skypro_homeworks.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/add")
    public String addEmployees(
            @RequestParam("fullNames") List<String> fullNames,
            @RequestParam("departaments") List<Integer> departaments,
            @RequestParam("salaries") List<Double> salaries) {

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < fullNames.size(); i++) {
            Employee employee = new Employee(
                    fullNames.get(i),
                    departaments.get(i),
                    salaries.get(i)
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

    @GetMapping("/employee/print-full-names")
    public List<String> printFullNames() {
        return employeeService.printFullNames();
    }

    @GetMapping("/employee/get-by-id")
    public String getEmployeeById(@RequestParam("id") int id) {
        return employeeService.getEmployeeById(id);
    }
}