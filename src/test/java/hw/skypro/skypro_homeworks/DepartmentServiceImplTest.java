package hw.skypro.skypro_homeworks;

import hw.skypro.skypro_homeworks.exceptions.DepartmentWasNotFound;
import hw.skypro.skypro_homeworks.model.Employee;
import hw.skypro.skypro_homeworks.service.DepartmentService;
import hw.skypro.skypro_homeworks.service.DepartmentServiceImpl;
import hw.skypro.skypro_homeworks.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    private DepartmentService departmentService;

    private Employee ivanov;
    private Employee petrov;
    private Employee sidorov;
    private Employee kuznecova;

    @BeforeEach
    void setUp() {
        departmentService = new DepartmentServiceImpl(employeeService);

        ivanov = new Employee(
                "Иванов Иван Иванович",
                1,
                10000
        );

        petrov = new Employee(
                "Петров Петр Петрович",
                1,
                15000
        );

        sidorov = new Employee(
                "Сидоров Сидор Сидорович",
                2,
                12000
        );

        kuznecova = new Employee(
                "Кузнецова Анна Сергеевна",
                3,
                20000
        );
    }

    @Test
    void shouldReturnEmployeesFromDepartment() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov,
                kuznecova
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        List<Employee> result =
                departmentService.getEmployeesByDepartment(1);

        assertEquals(
                List.of(ivanov, petrov),
                result
        );

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnEmptyListWhenDepartmentDoesNotExist() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        List<Employee> result =
                departmentService.getEmployeesByDepartment(999);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnEmptyListWhenEmployeeListIsEmpty() {
        when(employeeService.getEmployeeList())
                .thenReturn(List.of());

        List<Employee> result =
                departmentService.getEmployeesByDepartment(1);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnSalarySumForDepartment() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        double result =
                departmentService.getSalarySumByDepartment(1);

        assertEquals(25000, result);

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnZeroSalarySumWhenDepartmentDoesNotExist() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        double result =
                departmentService.getSalarySumByDepartment(999);

        assertEquals(0, result);

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnZeroSalarySumWhenEmployeeListIsEmpty() {
        when(employeeService.getEmployeeList())
                .thenReturn(List.of());

        double result =
                departmentService.getSalarySumByDepartment(1);

        assertEquals(0, result);

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnEmployeeWithMaximumSalary() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        Employee result =
                departmentService.getEmployeeWithMaxSalary(1);

        assertEquals(petrov, result);

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnEmployeeWithMinimumSalary() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        Employee result =
                departmentService.getEmployeeWithMinSalary(1);

        assertEquals(ivanov, result);

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldThrowExceptionWhenFindingMaximumSalaryInMissingDepartment() {
        when(employeeService.getEmployeeList())
                .thenReturn(List.of(ivanov, petrov));

        assertThrows(
                DepartmentWasNotFound.class,
                () -> departmentService.getEmployeeWithMaxSalary(999)
        );

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldThrowExceptionWhenFindingMinimumSalaryInMissingDepartment() {
        when(employeeService.getEmployeeList())
                .thenReturn(List.of(ivanov, petrov));

        assertThrows(
                DepartmentWasNotFound.class,
                () -> departmentService.getEmployeeWithMinSalary(999)
        );

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldGroupEmployeesByDepartment() {
        List<Employee> employees = List.of(
                ivanov,
                petrov,
                sidorov,
                kuznecova
        );

        when(employeeService.getEmployeeList()).thenReturn(employees);

        Map<Integer, List<Employee>> result =
                departmentService.getEmployeesByDepartment();

        assertEquals(3, result.size());

        assertEquals(
                List.of(ivanov, petrov),
                result.get(1)
        );

        assertEquals(
                List.of(sidorov),
                result.get(2)
        );

        assertEquals(
                List.of(kuznecova),
                result.get(3)
        );

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldReturnEmptyMapWhenEmployeeListIsEmpty() {
        when(employeeService.getEmployeeList())
                .thenReturn(List.of());

        Map<Integer, List<Employee>> result =
                departmentService.getEmployeesByDepartment();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(employeeService).getEmployeeList();
    }

    @Test
    void shouldUseEmployeeServiceOnlyOnceWhenGroupingEmployees() {
        when(employeeService.getEmployeeList())
                .thenReturn(List.of(ivanov, petrov));

        departmentService.getEmployeesByDepartment();

        verify(employeeService, times(1)).getEmployeeList();
        verifyNoMoreInteractions(employeeService);
    }
}