import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    int income = (int) (0 + 50_000_000 * Math.random());
    List<Integer> employees = new ArrayList();

    public void hire(int employee) {
        employees.add(employee);
    }

    public void hireAll(ArrayList<Integer> employeeAll) {
        employees.addAll(employeeAll);
    }

    public void fire(int employee) {
        employees.remove(employee);
    }

    public int getIncome() { return income; }

//    @Override
//    public int compareTo(Company company) {
//        if (getMonthSalary() > company.getMonthSalary()) {
//            return 1;
//        }
//        if (getEmployees().get(0) > company.getEmployees().get(0)) {
//            return -1;
//        }
//        return 0;
//
//    }


    List<Integer> getTopSalaryStaff(int count) {
        int size = employees.size();
        System.out.println("Список " + count + " зарплат по убыванию:");
        Collections.sort(employees);
        Collections.reverse(employees);

        int i = 0;
        for (; count > 0; count--) {
            i++;
            System.out.println(i + ". " + employees.get(i) + " руб.");
        }
        return employees;
    }

    List<Integer> getLowestSalaryStaff(int count) {
        int size = employees.size();
        System.out.println("Список " + count + " зарплат по возрастанию:");
        Collections.sort(employees);
        Collections.reverse(employees);

        int i = 0;
        int x = size - 1;
        try {
            for (; count > 0; count--) {
                i++;
                x--;
                System.out.println(i + ". " + employees.get(x) + " руб.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Ошибка, всего сотрудников - " + size + ", значение должно быть равно или меньше");
        }
        return employees;

    }
//    List<Integer> getLowestSalaryStaff(int count) {
//        System.out.println("Список " + count + " зарплат по возрастанию:");
//        employeesLowestSalaryStaff.addAll(employees);
//        Collections.sort(employeesLowestSalaryStaff);
//
//        int size = employeesLowestSalaryStaff.size();
//        int i = 0;
//        try {
//            employeesLowestSalaryStaff.subList(count, size).clear();
//            for (int element : employeesLowestSalaryStaff) {
//                i++;
//                System.out.println(i + ". " + element + " руб.");
//            }
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            System.out.println("Ошибка, всего сотрудников - " + size + ", значение должно быть равно или меньше");
//        }
//        return employeesLowestSalaryStaff;
//
//    }
}





