class TopManager implements Employee {

    Integer salary = 100_000;

    public TopManager(Company company) {
        int income = company.getIncome();
        if (income > 10_000_000) {
            salary += 150_000;
        }
        salary = getMonthSalary();
    }

    @Override
    public Integer getMonthSalary() {
        int monthSalary = salary;
        return monthSalary;
    }
}