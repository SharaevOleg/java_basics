public class Operator implements Employee {
    String name;
    Integer salary;

    public Operator(Company company) {
        this.name = getClass().getName();
        this.salary = getMonthSalary();
    }

    @Override
    public Integer getMonthSalary() {
        int monthSalary = 60000;
        return monthSalary;
    }
}
