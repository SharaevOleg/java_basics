class TopManager implements Employee {

    int monthSalary = 100_000;

    public TopManager(Company company) {
        int income = company.getIncome();
        if (income > 10_000_000) {
            monthSalary += 150_000;
        }
        company.hire(getMonthSalary());
    }

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }
}