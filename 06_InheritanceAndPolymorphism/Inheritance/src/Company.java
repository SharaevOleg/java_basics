import java.util.*;

public class Company {

    int income = (int) (0 + 50_000_000 * Math.random());

    List<Employee> employees = new ArrayList();


    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(List<Employee> employeeAll) {
        employees.addAll(employeeAll);
    }

    public void fire(int i) {
        employees.remove(i);
    }

    public int getIncome() {
        return income;
    }

    List<Employee> getTopSalaryStaff(int count) {
        System.out.println("Список " + count + " зарплат по возрастанию:");
        employees.sort(Comparator.comparing(Employee::getMonthSalary));
        employees.stream().limit(count).forEach(employee -> System.out.println(employee.getMonthSalary() + " руб."));
        return employees;
    }

    List<Employee> getLowestSalaryStaff(int count) {
        System.out.println("Список " + count + " зарплат по убыванию:");
        employees.sort(Comparator.comparing(Employee::getMonthSalary));
        Collections.reverse(employees);
        employees.stream().limit(count).forEach(employee -> System.out.println(employee.getMonthSalary() + " руб."));
        return employees;
    }
}


