package hw.skypro.skypro_homeworks.service;

import hw.skypro.skypro_homeworks.exceptions.EmployeeWasNotFound;
import hw.skypro.skypro_homeworks.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();

    @Override
    public List<Employee> getEmployeeList() {
        return List.copyOf(employeeList);
    }

    @Override
    public String initEmployees() {
        employeeList.addAll(List.of(
                new Employee("Иванов Иван Иванович", 1, 10000),
                new Employee("Петров Петр Петрович", 2, 12000),
                new Employee("Сидоров Сидор Сидорович", 3, 11000),
                new Employee("Кузнецова Анна Сергеевна", 3, 15000),
                new Employee("Смирнова Ольга Викторовна", 1, 13000),
                new Employee("Ковалев Алексей Дмитриевич", 2, 14000),
                new Employee("Федорова Мария Александровна", 5, 16000),
                new Employee("Тихонов Сергей Валерьевич", 4, 11500),
                new Employee("Попова Екатерина Павловна", 4, 12500),
                new Employee("Григорьев Артем Юрьевич", 5, 17000)
        ));

        return "Успешное инициализирование тестовых данных!";
    }

    @Override
    public List<String> printEmployees() {
        return employeeList.stream()
                .map(Employee::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> printFullNames() {
        return employeeList.stream()
                .map(Employee::getFullName)
                .collect(Collectors.toList());
    }

    @Override
    public String addEmployees(List<Employee> employees) {
        int initialSize = employeeList.size();

        employees.stream()
                .filter(employee -> employeeList.stream()
                        .noneMatch(existingEmployee ->
                                Objects.equals(
                                        employee.getFullName(),
                                        existingEmployee.getFullName()
                                )
                        )
                )
                .forEach(employeeList::add);

        int addedEmployees = employeeList.size() - initialSize;

        if (addedEmployees == 0) {
            throw new RuntimeException(
                    "Введённый(-ые) сотрудники не были добавлены, так как они уже есть."
            );
        }

        return "Было добавлено " + addedEmployees
                + " новых сотрудников из " + employees.size() + " введённых!";
    }

    @Override
    public String deleteEmployee(int id) {
        Employee employee = employeeList.stream()
                .filter(currentEmployee -> currentEmployee.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new EmployeeWasNotFound(
                                "Сотрудник с id " + id + " не найден!"
                        )
                );

        employeeList.remove(employee);

        return employee + " был успешно найден и удалён!";
    }

    @Override
    public String getEmployeeById(int id) {
        Optional<String> result = employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .map(employee ->
                        "Сотрудник был успешно найден: " + employee
                )
                .findFirst();

        return result.orElseThrow(() ->
                new EmployeeWasNotFound(
                        "Сотрудник с id " + id + " не найден!"
                )
        );
    }
}