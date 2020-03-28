class TopManager implements Employee {
    private Company company = new Company();

    @Override
    public int getMonthSalary() {
        int monthSalary = 100_000;
        int income = company.getIncome();
        if (income > 10_000_000) {
            monthSalary = monthSalary + 150_000;
        }
        return monthSalary;
    }
}