public class Loader {

    public static void main(String[] args) {

        Company company = new Company();

        for (int i = 0; i < 180; i++) {
            Operator operator = new Operator();
            company.hire(operator.getMonthSalary());
        }
        for (int i = 0; i < 80; i++) {
            Manager manager = new Manager();
            company.hire(manager.getMonthSalary());
        }
        for (int i = 0; i < 10; i++) {
            TopManager topManager = new TopManager();
            company.hire(topManager.getMonthSalary());
        }

        company.getTopSalaryStaff(15);
        company.getLowestSalaryStaff(30);

        int size = (company.employees.size()) / 2;
        for (int i = 0; i < size; i++) {
            int randomEmploye = (int) (0 + size * Math.random());
            company.fire(randomEmploye);
        }

        company.getTopSalaryStaff(15);
        company.getLowestSalaryStaff(30);

    }
}


