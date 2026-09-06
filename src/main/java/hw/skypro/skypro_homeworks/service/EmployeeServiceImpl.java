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
    private final List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    @Override
    public List<Employee> getEmployeeList() {
        return EMPLOYEE_LIST;
    }

    // Инициализирует автоматичесую БД с пользователями
    // Для ручной инициализации можно использовать - /employee/add?fullName=Иванов+Иван+Иванович&departament=1&salary=10000&fullName=Петров+Петр+Петрович&departament=2&salary=12000&fullName=Сидоров+Сидор+Сидорович&departament=3&salary=11000&fullName=Кузнецова+Анна+Сергеевна&departament=3&salary=15000&fullName=Смирнова+Ольга+Викторовна&departament=1&salary=13000&fullName=Ковалев+Алексей+Дмитриевич&departament=2&salary=14000&fullName=Федорова+Мария+Александровна&departament=5&salary=16000&fullName=Тихонов+Сергей+Валерьевич&departament=4&salary=11500&fullName=Попова+Екатерина+Павловна&departament=4&salary=12500&fullName=Григорьев+Артем+Юрьевич&departament=5&salary=17000
    //    @Override
    //    public String initEmployees() {
    //        EMPLOYEE_LIST.addAll(List.of(
    //                new Employee("Иванов Иван Иванович", 1, 10000),
    //                new Employee("Петров Петр Петрович", 2, 12000),
    //                new Employee("Сидоров Сидор Сидорович", 3, 11000),
    //                new Employee("Кузнецова Анна Сергеевна", 3, 15000),
    //                new Employee("Смирнова Ольга Викторовна", 1, 13000),
    //                new Employee("Ковалев Алексей Дмитриевич", 2, 14000),
    //                new Employee("Федорова Мария Александровна", 5, 16000),
    //                new Employee("Тихонов Сергей Валерьевич", 4, 11500),
    //                new Employee("Попова Екатерина Павловна", 4, 12500),
    //                new Employee("Григорьев Артем Юрьевич", 5, 17000)
    //        ));
    //        return "Успешное инициализирование тестовых данных!";
    //    }

    @Override
    public List<String> printEmployees() {
        return EMPLOYEE_LIST.stream()
                .map(Employee::toString)
                .collect(Collectors.toList());
    }

    @Override
    // done
    public List<String> printFullNames() {
        return EMPLOYEE_LIST.stream()
                .map(Employee::getFullName)
                .collect(Collectors.toList());
    }

    @Override
    public String addEmployees(List<Employee> employees) {
        int countOfEmployees = EMPLOYEE_LIST.size();

        employees.stream()
                .filter(employee -> EMPLOYEE_LIST.stream()
                        .noneMatch(employeeExist -> Objects.equals(employee.getFullName(), employeeExist.getFullName())))
                .forEach((EMPLOYEE_LIST::add));

        if (countOfEmployees == EMPLOYEE_LIST.size()) {
            throw new RuntimeException("Введённый(-ые) сотрудники не были добавлены, так как они уже есть.");
        }

        return "Было добавлено " + (EMPLOYEE_LIST.size() - countOfEmployees) + " новых сотрудников из " + countOfEmployees + " введённых!";
    }

    @Override
    public String deleteEmployee(int id) {
        Employee employee = EMPLOYEE_LIST.stream()
                .filter(e -> e.getID() == id)
                .findFirst()
                .orElseThrow(() ->
                        new EmployeeWasNotFound("Сотрудник с id " + id + " не найден!"));

        EMPLOYEE_LIST.remove(employee);

        return employee.toString() + " был успешно найден и удалён!";
    }

    @Override
    public String getEmployeeById(int id) {

        Optional<String> result = EMPLOYEE_LIST.stream()
                .filter(employee -> employee.getID() == id)
                .map(employee -> "Сотрудник был успешно найден: " + employee.toString())
                .findAny();

        return result.orElseThrow(() -> new EmployeeWasNotFound("Сотрудник с id " + id + " не найден!"));
    }
}
