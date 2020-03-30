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
        int size = employees.size();
        System.out.println("Список " + count + " зарплат по убыванию:");
        Collections.sort(employees, ((o1, o2) -> {
            return o1.getMonthSalary().compareTo(o2.getMonthSalary());
        }));
        Collections.reverse(employees);

        int i = 0;
        for (; count > 0; count--) {
            i++;
            System.out.println(i + ". " + employees.get(i).getMonthSalary() + " руб.");
        }
        return employees;
    }

    List<Employee> getLowestSalaryStaff(int count) {
        int size = employees.size();
        System.out.println("Список " + count + " зарплат по возрастанию:");

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary().compareTo(o2.getMonthSalary());
            }
        });

        int i = 0;
        int x = size - 1;
        try {
            for (; count > 0; count--) {
                i++;
                x--;
                System.out.println(i + ". " + employees.get(x).getMonthSalary() + " руб.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Ошибка, всего сотрудников - " + size + ", значение должно быть равно или меньше");
        }
        return employees;

    }
}


