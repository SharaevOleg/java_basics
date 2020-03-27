class TopManager extends Company implements Employee {

    @Override
    public int getMonthSalary() {
        int monthSalary = 100_000;
//        Company company = new Company();
//        int income = company.income;
        if (income > 10_000_000) {
            monthSalary = monthSalary + 150_000;
        }
        return monthSalary;
    }
}