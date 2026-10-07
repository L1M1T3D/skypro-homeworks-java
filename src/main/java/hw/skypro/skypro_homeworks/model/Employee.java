package hw.skypro.skypro_homeworks.model;

import java.util.Objects;

public class Employee {

    private static int idCounter = 0;

    private final String fullName;
    private final int id;
    private int departament;
    private double salary;

    public Employee(String fullName, int departament, double salary) {
        idCounter++;
        this.id = idCounter;
        this.fullName = fullName;
        this.departament = departament;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartament() {
        return departament;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartament(int departament) {
        this.departament = departament;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        return departament == employee.departament
                && Double.compare(employee.salary, salary) == 0
                && id == employee.id
                && Objects.equals(fullName, employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, departament, salary, id);
    }

    @Override
    public String toString() {
        return "Сотрудник (id " + id + ") - '"
                + fullName
                + "', отдел №"
                + departament
                + ", ЗП "
                + salary
                + " руб.";
    }
}