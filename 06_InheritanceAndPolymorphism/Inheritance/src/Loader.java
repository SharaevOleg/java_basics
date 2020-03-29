public class Loader {

    public static void main(String[] args) {

        Company company = new Company();

        for (int i = 0; i < 180; i++) {
            new Operator(company);
        }
        for (int i = 0; i < 80; i++) {
            new Manager(company);
        }
        for (int i = 0; i < 10; i++) {
            new TopManager(company);
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


