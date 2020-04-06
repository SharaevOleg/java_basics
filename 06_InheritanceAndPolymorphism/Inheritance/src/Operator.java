public class Operator implements Employee {

    Integer salary;

    public Operator(Company company) {
        salary = 40000;
    }

    @Override
    public Integer getMonthSalary() {
        int monthSalary = salary;
        return monthSalary;
    }
}
