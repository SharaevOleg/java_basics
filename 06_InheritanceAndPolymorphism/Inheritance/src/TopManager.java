class TopManager implements Employee {
    String name;
    Integer salary = 100_000;

    public TopManager(Company company) {
        this.name = getClass().getName();
        int income = company.getIncome();
        if (income > 10_000_000) {
            salary += 150_000;
        }
        this.salary = getMonthSalary();
    }

    @Override
    public Integer getMonthSalary() {
        return salary;
    }
}