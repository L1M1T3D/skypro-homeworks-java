package hw.skypro.skypro_homeworks;

import hw.skypro.skypro_homeworks.exceptions.EmployeeWasNotFound;
import hw.skypro.skypro_homeworks.model.Employee;
import hw.skypro.skypro_homeworks.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void shouldReturnEmptyEmployeeListWhenServiceIsCreated() {
        assertTrue(employeeService.getEmployeeList().isEmpty());
    }

    @Test
    void shouldAddEmployee() {
        Employee employee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        String result = employeeService.addEmployees(List.of(employee));

        assertEquals(
                "Было добавлено 1 новых сотрудников из 1 введённых!",
                result
        );

        assertEquals(1, employeeService.getEmployeeList().size());
        assertEquals(employee, employeeService.getEmployeeList().getFirst());
    }

    @Test
    void shouldNotAddEmployeeWithDuplicateFullName() {
        Employee firstEmployee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        Employee duplicateEmployee = new Employee(
                "Иванов Иван Иванович",
                2,
                20000
        );

        employeeService.addEmployees(List.of(firstEmployee));

        assertThrows(
                RuntimeException.class,
                () -> employeeService.addEmployees(List.of(duplicateEmployee))
        );

        assertEquals(1, employeeService.getEmployeeList().size());
    }

    @Test
    void shouldAddOnlyUniqueEmployees() {
        Employee firstEmployee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        Employee secondEmployee = new Employee(
                "Петров Петр Петрович",
                2,
                12000
        );

        Employee duplicateEmployee = new Employee(
                "Иванов Иван Иванович",
                3,
                15000
        );

        employeeService.addEmployees(List.of(firstEmployee));

        String result = employeeService.addEmployees(
                List.of(secondEmployee, duplicateEmployee)
        );

        assertEquals(
                "Было добавлено 1 новых сотрудников из 2 введённых!",
                result
        );

        assertEquals(2, employeeService.getEmployeeList().size());
        assertTrue(
                employeeService.getEmployeeList().contains(secondEmployee)
        );
    }

    @Test
    void shouldDeleteEmployee() {
        Employee employee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        employeeService.addEmployees(List.of(employee));

        String result = employeeService.deleteEmployee(employee.getId());

        assertTrue(result.contains("был успешно найден и удалён!"));
        assertTrue(employeeService.getEmployeeList().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenDeletingMissingEmployee() {
        assertThrows(
                EmployeeWasNotFound.class,
                () -> employeeService.deleteEmployee(999)
        );
    }

    @Test
    void shouldFindEmployeeById() {
        Employee employee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        employeeService.addEmployees(List.of(employee));

        String result = employeeService.getEmployeeById(employee.getId());

        assertTrue(result.contains("Сотрудник был успешно найден"));
        assertTrue(result.contains(employee.getFullName()));
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {
        assertThrows(
                EmployeeWasNotFound.class,
                () -> employeeService.getEmployeeById(999)
        );
    }

    @Test
    void shouldPrintEmployees() {
        Employee firstEmployee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        Employee secondEmployee = new Employee(
                "Петров Петр Петрович",
                2,
                12000
        );

        employeeService.addEmployees(
                List.of(firstEmployee, secondEmployee)
        );

        List<String> result = employeeService.printEmployees();

        assertEquals(2, result.size());
        assertTrue(result.get(0).contains(firstEmployee.getFullName()));
        assertTrue(result.get(1).contains(secondEmployee.getFullName()));
    }

    @Test
    void shouldPrintFullNames() {
        Employee firstEmployee = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        Employee secondEmployee = new Employee(
                "Петров Петр Петрович",
                2,
                12000
        );

        employeeService.addEmployees(
                List.of(firstEmployee, secondEmployee)
        );

        List<String> result = employeeService.printFullNames();

        assertEquals(
                List.of(
                        "Иванов Иван Иванович",
                        "Петров Петр Петрович"
                ),
                result
        );
    }

    @Test
    void shouldInitializeEmployees() {
        String result = employeeService.initEmployees();

        assertEquals(
                "Успешное инициализирование тестовых данных!",
                result
        );

        assertEquals(10, employeeService.getEmployeeList().size());
    }
}